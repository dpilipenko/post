package com.dpilipenko.post;

import java.util.ArrayList;
import java.util.Arrays;

public class Envelope {
	private String[] toAddresses;
	private String[] ccAddresses;
	private String[] bccAddresses;
	private Message message;
	public String[] getToAddresses() {
		return toAddresses;
	}
	public void setToAddresses(String[] toAddresses) {
		this.toAddresses = toAddresses;
	}
	public void addToAddress(String toAddress) {
		if (toAddresses == null) {
			toAddresses = new String[] { toAddress };
		} else {
			toAddresses = addToArray(toAddress, toAddresses);
		}
	}
	public String[] getCcAddresses() {
		return ccAddresses;
	}
	public void setCcAddresses(String[] ccAddresses) {
		this.ccAddresses = ccAddresses;
	}
	public void addCcAddress(String ccAddress) {
		if (ccAddresses == null) {
			ccAddresses = new String[] { ccAddress };
		} else {
			ccAddresses = addToArray(ccAddress, ccAddresses);
		}
	}
	public String[] getBccAddresses() {
		return bccAddresses;
	}
	public void setBccAddresses(String[] bccAddresses) {
		this.bccAddresses = bccAddresses;
	}
	public void addBccAddress(String bccAddress) {
		if (bccAddresses == null) {
			bccAddresses = new String[] { bccAddress };
		} else {
			bccAddresses = addToArray(bccAddress, bccAddresses);
		}
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	private String[] addToArray(String val, String[] array) {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(array));
		list.add(val);
		return (String[]) list.toArray();
	}
}
