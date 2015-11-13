package com.dpilipenko.post.util;

import java.io.IOException;
import java.util.Properties;

public class Conf {
	private String from;
	private String host;
	private String port;
	private String username;
	private String password;
	private String auth;
	private String protocol;
	
	public static Conf fetchInstance() {
		Properties props = getPostProperties();
		Conf conf = new Conf();
		conf.from = props.getProperty("post.smtp.from");
		conf.host = props.getProperty("post.smtp.host");
		conf.port = props.getProperty("post.smtp.port");
		conf.username = props.getProperty("post.smtp.username");
		conf.password = props.getProperty("post.smtp.password");
		conf.auth = (conf.username != null && conf.password != null ? "true" : "false");
		conf.protocol = "smtp";
		return conf;
	}
	
	protected static Properties getPostProperties() {
		final Properties props = new Properties();
		try {
			props.load(Conf.class.getClassLoader().getResourceAsStream("post.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
}
