package com.sales.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
    /*@Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String host;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private UserService userService;

    public void sendMail(MailDto mail) {
        new Thread(() -> {
            try {
                String[] recipients = filterRecipients(mail.getTo());
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo(recipients);
                helper.setSubject(mail.getSubject());
                helper.setFrom(host);
                message.setContent(getHtmlTemplate(mail.getTemplateName(), mail.getParameters()),
                        MimeTypeUtils.TEXT_HTML_VALUE);
                addAttachment(mail, helper);
                if (!Collections.isEmpty(Arrays.asList(recipients))) {
                    mailSender.send(message);
                }
            } catch (MessagingException | MailException e) {
                logger.error("Error sending email: ", e);
            }
        }).start();
    }

    private String[] filterRecipients(String[] emails) {
        List<String> list = new ArrayList<>(Arrays.asList(emails));
        list.remove(userService.findSystemUser().getEmail());
        return list.toArray(new String[0]);
    }*/
/*
    private void addAttachment(MailDto mail, MimeMessageHelper helper) throws MessagingException {
        if (mail.getMailAttachment() != null) {
            if (mail.getMailAttachment().getAttachment() instanceof DataSource) {
                helper.addAttachment(mail.getMailAttachment().getAttachmentName(),
                        (DataSource) mail.getMailAttachment().getAttachment());
            } else if (mail.getMailAttachment().getAttachment() instanceof File) {
                helper.addAttachment(mail.getMailAttachment().getAttachmentName(),
                        (File) mail.getMailAttachment().getAttachment());
            } else if (mail.getMailAttachment().getAttachment() instanceof InputStreamSource) {
                helper.addAttachment(mail.getMailAttachment().getAttachmentName(),
                        (InputStreamSource) mail.getMailAttachment().getAttachment());
            }
        }
    }*/
/*
    private String getHtmlTemplate(String templateName, Map<String, Object> parameters) {
        final Context context = new Context();
        context.setVariables(parameters);
        return springTemplateEngine.process(templateName, context);
    }*/
}
