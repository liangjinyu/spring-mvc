package cn.nj.ljy.mvc.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public void document(HttpServletRequest request, HttpServletResponse response, @PathVariable int size) {

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

}
