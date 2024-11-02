package org.spring.rest_registrarionmail.service.mail;

public interface MailService {
    void sendEmail(String to, String subject, String body);
}
