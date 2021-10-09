package com.java.qa.sccm.pojopages;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "customer_id", "customer_name", "short_name", "app_data" })
@Generated("jsonschema2pojo")
public class CreateCustomer {

	@JsonProperty("customer_id")
	private String customerId;
	@JsonProperty("customer_name")
	private String customerName;

	@JsonProperty("short_name")
	private String shortName;

	@JsonProperty("app_data")
	private AppData appData;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("customer_id")
	public String getCustomerId() {
		return customerId;
	}

	@JsonProperty("customer_id")
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("customer_name")
	public String getCustomerName() {
		return customerName;
	}

	@JsonProperty("customer_name")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonProperty("short_name")
	public String getShortName() {
		return shortName;
	}

	@JsonProperty("short_name")
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@JsonProperty("app_data")
	public AppData getAppData() {
		return appData;
	}

	@JsonProperty("app_data")
	public void setAppData(AppData appData) {
		this.appData = appData;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}