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
@JsonPropertyOrder({ "device_id", "service_specific_id", "adl", "parameters" })
@Generated("jsonschema2pojo")
public class EndpointDevice {

	@JsonProperty("device_id")
	private String deviceId;
	@JsonProperty("service_specific_id")
	private String serviceSpecificId;
	@JsonProperty("adl")
	private Object adl;
	@JsonProperty("parameters")
	private Parameters parameters;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("device_id")
	public String getDeviceId() {
		return deviceId;
	}

	@JsonProperty("device_id")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@JsonProperty("service_specific_id")
	public String getServiceSpecificId() {
		return serviceSpecificId;
	}

	@JsonProperty("service_specific_id")
	public void setServiceSpecificId(String serviceSpecificId) {
		this.serviceSpecificId = serviceSpecificId;
	}

	@JsonProperty("adl")
	public Object getAdl() {
		return adl;
	}

	@JsonProperty("adl")
	public void setAdl(Object adl) {
		this.adl = adl;
	}

	@JsonProperty("parameters")
	public Parameters getParameters() {
		return parameters;
	}

	@JsonProperty("parameters")
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
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
