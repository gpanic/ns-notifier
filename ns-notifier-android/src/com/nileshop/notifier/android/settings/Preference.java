package com.nileshop.notifier.android.settings;

public enum Preference {
	SERVICE_RUNNING ("service_running"),
	TWITTER_CONSUMER_KEY ("twitter_consumer_key"),
	TWITTER_CONSUMER_SECRET ("twitter_consumer_secret"),
	LAST_CHECK ("last_check");
	
	private final String name;
	
	private Preference(String name) {
		this.name = name;	
	}
	
	public String getName() {
		return name;
	}

}
