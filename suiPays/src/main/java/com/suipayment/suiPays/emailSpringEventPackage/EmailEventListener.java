package com.suipayment.suiPays.emailSpringEventPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailEventListener {

    @Autowired
    private EmailService emailService;

    public EmailEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async  // Ensures it runs in a separate thread
    @EventListener
    public void handleEmailEvent(EmailEvent event) {
        emailService.sendEmail(event.getTo(), event.getSubject(), event.getText());
    }

    @Override
    public String toString() {
        return "EmailEventListener{" +
                "emailService=" + emailService +
                '}';
    }
}
