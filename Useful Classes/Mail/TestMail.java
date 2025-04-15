package utilities;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class TestMail {

	public static void main(String[] args) throws AddressException, MessagingException {
		// TODO Auto-generated method stub

		
		
		MonitoringMail mail = new MonitoringMail();
//		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody);
		
	}

}
