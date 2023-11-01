package com.project.youandme_schedule.verification.smsSender;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    @Value("${twilio.accountSid}")
    private String ACCOUNT_SID;

    @Value("${twilio.authToken}")
    private String AUTH_TOKEN;

    @Value("${twilio.phoneNumber}")
    private String TWILIO_PHONE_NUMBER;

    private String verificationCode;

    public void sendSms (String toPhone, String verificationCode) {
        this.verificationCode = verificationCode;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(toPhone),
                new PhoneNumber(TWILIO_PHONE_NUMBER),
                "Verification code: " + verificationCode)
                .create();

        System.out.println("Message SID: " + message.getSid());

    }

    public String getVerificationCode() {
        return verificationCode;
    }

}
