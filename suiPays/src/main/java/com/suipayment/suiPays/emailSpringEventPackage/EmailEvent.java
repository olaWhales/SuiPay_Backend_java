package com.suipayment.suiPays.emailSpringEventPackage;

import org.springframework.context.ApplicationEvent;
public class EmailEvent extends ApplicationEvent {
    private final String to;
    private final String subject;
    private final String text;

    public EmailEvent(Object source, String to, String subject, String text) {
        super(source);
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() { return to; }
    public String getSubject() { return subject; }
    public String getText() { return text; }

    @Override
    public String toString() {
        return "EmailEvent{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", source=" + source +
                '}';
    }
}
