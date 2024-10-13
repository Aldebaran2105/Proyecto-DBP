package com.api.rest.canvas2.Email.domain;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void correoSignIn(String to, String name, String lastname) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("lastname", lastname);

        String process = templateEngine.process("SignInEmail.html", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setTo(to);
        helper.setText(process, true);
        helper.setSubject("Bienvenido a UTEC++");

        mailSender.send(message);
    }

    @Async
    public void correoZoomMeeting(String to, String courseName, LocalDateTime meetingDate, String zoomLink) throws MessagingException {
        Context context = new Context();
        context.setVariable("courseName", courseName);
        context.setVariable("meetingDate", meetingDate.toString());
        context.setVariable("zoomLink", zoomLink);

        String process = templateEngine.process("ZoomMeetingEmail.html", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setTo(to);
        helper.setText(process, true);
        helper.setSubject("Enlace para tu próxima clase en UTEC++");

        mailSender.send(message);
    }
}
