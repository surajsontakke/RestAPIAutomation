package com.java.qa.sccm.pojopages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "transaction_id", "endpoint_devices" })
@Generated("jsonschema2pojo")
public class Step2Transaction {

	@JsonProperty("transaction_id")
	private String transactionId;
	@JsonProperty("endpoint_devices")
	private List<EndpointDevice> endpointDevices = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("transaction_id")
	public String getTransactionId() {
		return transactionId;
	}

	@JsonProperty("transaction_id")
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@JsonProperty("endpoint_devices")
	public List<EndpointDevice> getEndpointDevices() {
		return endpointDevices;
	}

	@JsonProperty("endpoint_devices")
	public void setEndpointDevices(List<EndpointDevice> endpointDevices) {
		this.endpointDevices = endpointDevices;
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
