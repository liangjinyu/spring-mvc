package cn.nj.ljy.mvc.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.nj.ljy.mvc.util.ImgUtil;

@Controller
@RequestMapping(value = "file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    private static final int DEFAULT_SIZE = 400;

    /**
     * 根据指定大小的压缩图片 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @version [V1.0, 2017年7月17日]
     * @param request
     * @param response
     * @param size
     */
    @RequestMapping(value = "/img/{size}", method = RequestMethod.GET)
    public void size(HttpServletRequest request, HttpServletResponse response, @PathVariable int size) {

        // 百度图标
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        // 压缩至指定大小
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImgUtil.compressImg(url, size);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // 写回客户端
        InputStream is = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "gif", os);
            is = new ByteArrayInputStream(os.toByteArray());
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    @RequestMapping(value = "/img/limit/{limit}", method = RequestMethod.GET)
    public void limit(HttpServletRequest request, HttpServletResponse response, @PathVariable int limit) {

        // 百度图标
        // String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        String url = "https://mime-public.oss-cn-hangzhou.aliyuncs.com/4400_MW866O71-YJPLR5G9UM9572JVITFM2-OBUKLV3J-0.jpg";

        boolean isSizeOk = false;
        int size = DEFAULT_SIZE;
        BufferedImage originImg = null;
//        try {
//            URL imgRul = new URL(url);
//            originImg = ImageIO.read(imgRul);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//        
        try {
            originImg = ImageIO.read(new File("F:\\tmp\\sp_img7@3x.png"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
//        BufferedImage bufferedImage = ImgUtil.compressImgWithLimit(originImg,size,limit);
        while (!isSizeOk) {
            // 压缩至指定大小
            InputStream is = null;
            BufferedImage bufferedImage = null;
            try {
                size = Math.min( (int)(originImg.getWidth()*0.6), size);
                
                bufferedImage = ImgUtil.compressImg(originImg, size);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", os);
                int storeSize = ((os.size() - 1) / 1024) + 1;
                if (storeSize <= limit) {
                    is = new ByteArrayInputStream(os.toByteArray());
                    IOUtils.copy(is, response.getOutputStream());
                    isSizeOk = true;
                    File newImg = new File("F:\\tmp\\test.png");
                    ImageIO.write(bufferedImage, "png", newImg);

                    LOGGER.info("os.size() = " + os.size());
//                    LOGGER.info("newImg.length() = " + newImg.length());

                } else {
                    int tempSize1 = (int) (size / Math.sqrt(((double) storeSize / limit)));
                    int tempSize2 = (int) (size * 0.6);
                    size = Math.min(tempSize1, tempSize2);
                }

            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
    }

}
