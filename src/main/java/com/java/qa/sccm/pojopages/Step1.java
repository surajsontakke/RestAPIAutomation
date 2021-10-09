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

/*
 * { "service_order_id": "7654", "customer_id": "10034567", "sub_service_id":
 * "1.1.1.1", "endpoint_device_count": 1, "portUD": "no", "adl_type": "false",
 * "qos": false }
 */

public class Step1 {

	String service_order_id;
	String customer_id;
	String sub_service_id;
	String endpoint_device_count;
	String portUD;
	String adl_type;
	String qos;

	public String getService_order_id() {
		return service_order_id;
	}

	public void setService_order_id(String service_order_id) {
		this.service_order_id = service_order_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getSub_service_id() {
		return sub_service_id;
	}

	public void setSub_service_id(String sub_service_id) {
		this.sub_service_id = sub_service_id;
	}

	public String getEndpoint_device_count() {
		return endpoint_device_count;
	}

	public void setEndpoint_device_count(String endpoint_device_count) {
		this.endpoint_device_count = endpoint_device_count;
	}

	public String getPortUD() {
		return portUD;
	}

	public void setPortUD(String portUD) {
		this.portUD = portUD;
	}

	public String getAdl_type() {
		return adl_type;
	}

	public void setAdl_type(String adl_type) {
		this.adl_type = adl_type;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}

}
