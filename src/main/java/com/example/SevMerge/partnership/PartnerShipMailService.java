package com.example.SevMerge.partnership;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerShipMailService {

    private final JavaMailSender mailSender;

    public void sendPartnerShipMail(PartnerShipRequest request) {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo("dkswnsgus88@naver.com");
            helper.setReplyTo(request.getEmail());
            helper.setText(
                    "[제휴문의] \n" +
                            "회사이름: " + request.getCompanyName() + "\n" +
                            "문의자 메일: " + request.getEmail() + "\n" +
                            "제휴내용:\n" + request.getContent()
            );
            if (request.getPartnerFile() != null && !request.getPartnerFile().isEmpty()) {
                helper.addAttachment(
                        request.getPartnerFile().getOriginalFilename(),
                        request.getPartnerFile()
                );
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
