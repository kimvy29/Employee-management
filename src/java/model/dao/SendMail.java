/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.entity.Contract;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
public class SendMail {
    public static void sendMailAccount(Employee e, int type, String pass) {
        try {
            Properties mailSer = System.getProperties();
            mailSer.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            mailSer.put("mail.smtp.host", "smtp.gmail.com");
            mailSer.put("mail.smtp.port", "587");
            mailSer.put("mail.smtp.auth", "true");
            mailSer.put("mail.smtp.starttls.enable", "true");

            Session getMail = Session.getInstance(mailSer,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("fptsoftware22@gmail.com", "xynksgwqekyxuetx");
                }
            });
            MimeMessage mailMess = new MimeMessage(getMail);
            mailMess.addRecipient(Message.RecipientType.TO, new InternetAddress(e.getEmail()));
            if (type == 1) {
                mailMess.setSubject("[THÔNG BÁO TẠO TÀI KHOẢN FPT]", "UTF-8");
                mailMess.setText("Dear " + e.getFullName() + ", \nChào mừng bạn tham gia vào đội ngũ công ty FPT.\n\nDưới đây là thông tin tài khoản đăng nhập vào trang quản lý của bạn:\nAccount: " + e.getUserName() + "\nPassword: " + pass
                        + "\n\nThông tin hợp đồng:\nLương cơ bản:" + new Contract(e).getSalaryBasic() + "\nNgày bắt đầu: " + new Contract(e).getFrDate() + "\nHạn hợp đồng: " + new Contract(e).getToDate() + "\n\nThank you and Warmest Regards,\nCông ty FPT", "UTF-8");
            } else {
                SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                java.util.Date date = new java.util.Date();
                mailMess.setSubject("[THÔNG BÁO RESET PASSWORD TÀI KHOẢN FPT]", "UTF-8");
                mailMess.setText("Dear " + e.getFullName() + ", \nPassword của bạn đã được reset theo yêu cầu vào lúc " + f.format(date) + "\n\nĐây là thông tin password mới của bạn: " + pass
                        + "\n\nThank you and Warmest Regards,\nCông ty FPT", "UTF-8");
            }

            Transport tran = getMail.getTransport("smtp");
            Transport.send(mailMess);
            tran.close();
        } catch (AddressException ex) {
            System.out.println(ex);
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        sendMailAccount(new Employee(1010), 0, "abc");
    }
}
