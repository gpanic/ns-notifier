package com.nileshop.notifier.android;

public enum Preference {
	SERVICE_RUNNING ("service_running");
	
	private final String name;
	
	private Preference(String name) {
		this.name = name;	
	}
	
	public String getName() {
		return name;
	}

}
