package com.kbc.rough;


import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kbc.utilities.MonitoringMail;
import com.kbc.utilities.TestConfig;



public class TestProperties  {
	

	    public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
	    	
	    	MonitoringMail mail = new MonitoringMail();
	    	String messageBody = "http://"+InetAddress.getLocalHost().getHostAddress() + ":8080/job/DataDrivenLiveProject/Extents_20Report/";
	    	System.out.println(messageBody);
	    	
	    	mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
	    }
}


