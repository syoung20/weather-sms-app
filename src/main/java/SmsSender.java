import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {

    public static final String ACCOUNT_SID = "Placeholder for Account SID";
    public static final String AUTH_TOKEN = "Placeholder for Auth Token";
    public static final String TWILIO_NUMBER = "Placeholder for Twilio Number";

    public static void sendSMS(String text) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("Placeholder for Receiving Phone Number"), new PhoneNumber(TWILIO_NUMBER), text).create();
        System.out.println(message.getSid());
    }
}
