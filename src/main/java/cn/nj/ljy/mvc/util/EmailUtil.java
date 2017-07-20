package cn.nj.ljy.mvc.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

/**
 * 参考： http://commons.apache.org/proper/commons-email/userguide.html
 * 
 * @author liang jinyu
 * @version [V1.0, 2017年7月20日]
 */
public class EmailUtil {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            sendSimpleMail();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void sendSimpleMail() throws Exception {

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("F:\\tmp\\3.0.2后台系统上线.xls");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("2017.7.20上线内容");
        attachment.setName("3.0.2后台系统上线.xls");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();

        email.setHostName("smtp.163.com");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator("liangjinyu0321@163.com", "xxxxxx"));
        email.setSSLOnConnect(true);
        // email.setHostName("mail.myserver.com");
        email.addTo("jinyu.liang@mi-me.com", "liangjinyu");
        email.setFrom("liangjinyu0321@163.com", "liangjinyu");
        email.setSubject("The systems will online");
        email.setMsg("Here is the systems will online this tonight");

        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();
    }

}
