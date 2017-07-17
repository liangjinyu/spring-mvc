package cn.nj.ljy.mvc.util;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImgUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImgUtil.class);
//    private static final int size = 300;

    public static BufferedImage compressImage(BufferedImage src, int width, int height) {
        return Scalr.resize(src, Method.ULTRA_QUALITY, Mode.FIT_EXACT, width, height);
    }

    public static BufferedImage compressImg(String url, int size) throws Exception  {

        URL imgRul = new URL(url);
        BufferedImage originImg = ImageIO.read(imgRul);
        BufferedImage newImg = compressImage(originImg, size,
                (int) (size * originImg.getHeight() / originImg.getWidth()));
        return newImg;
    }

    // public static void compressTestUrl(String url, String newImgPath) throws MalformedURLException {
    // System.out.println("图片压缩--------------------------" + url);
    //
    // URL imgRul = new URL(url);
    // try {
    //
    // BufferedImage originImg = ImageIO.read(imgRul.openStream());
    // File newImgFile = new File(newImgPath);
    // BufferedImage newImg = compressImage(originImg, size,
    // (int) (size * originImg.getHeight() / originImg.getWidth()));
    // ImageIO.write(newImg, "jpg", newImgFile);
    // System.out.println("压缩图片大小：" + newImgFile.length() / 1024 + "k");
    // System.out.println("压缩图片高：" + newImg.getHeight());
    // System.out.println("压缩图片宽：" + newImg.getWidth());
    //
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

    // public static void compressTest(String path) {
    // System.out.println("图片压缩--------------------------" + path);
    // File originImgFile = new File(path);
    // String name = originImgFile.getName();
    // String newFileName = name.substring(0, name.indexOf(".")) + "-new" + name.substring(name.indexOf("."));
    // File newImgFile = new File(originImgFile.getParent() + "/" + newFileName);
    // try {
    //
    // System.out.println("原始图片大小：" + originImgFile.length() / 1024 + "k");
    // BufferedImage originImg = ImageIO.read(originImgFile);
    // System.out.println("原始图片高：" + originImg.getHeight());
    // System.out.println("原始图片宽：" + originImg.getWidth());
    //
    // BufferedImage newImg = compressImage(originImg, size,
    // (int) (size * originImg.getHeight() / originImg.getWidth()));
    // ImageIO.write(newImg, "jpg", newImgFile);
    // System.out.println("压缩图片大小：" + newImgFile.length() / 1024 + "k");
    // System.out.println("压缩图片高：" + newImg.getHeight());
    // System.out.println("压缩图片宽：" + newImg.getWidth());
    //
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

    // public static void main(String[] args) {

    // compressTest("F:\\tmp\\test-pic.jpg");
    // compressTest("F:\\tmp\\testxxx-origin.jpg");

    // try {
    // compressTestUrl("https://mime-public.oss-cn-hangzhou.aliyuncs.com/4400_MW866O71-YJPLR5G9UM9572JVITFM2-OBUKLV3J-0.jpg","F:\\tmp\\testxxx.jpg");
    // } catch (MalformedURLException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // }
}
