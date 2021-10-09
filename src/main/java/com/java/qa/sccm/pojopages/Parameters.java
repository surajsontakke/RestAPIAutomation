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
@JsonPropertyOrder({ "customer_id", "route-map_name", "ipv6_address_+_subnet_mask", "topology_type" })
@Generated("jsonschema2pojo")
public class Parameters {

	@JsonProperty("customer_id")
	private String customerId;
	@JsonProperty("route-map_name")
	private String routeMapName;
	@JsonProperty("ipv6_address_+_subnet_mask")
	private String ipv6AddressSubnetMask;
	@JsonProperty("topology_type")
	private String topologyType;
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

	@JsonProperty("route-map_name")
	public String getRouteMapName() {
		return routeMapName;
	}

	@JsonProperty("route-map_name")
	public void setRouteMapName(String routeMapName) {
		this.routeMapName = routeMapName;
	}

	@JsonProperty("ipv6_address_+_subnet_mask")
	public String getIpv6AddressSubnetMask() {
		return ipv6AddressSubnetMask;
	}

	@JsonProperty("ipv6_address_+_subnet_mask")
	public void setIpv6AddressSubnetMask(String ipv6AddressSubnetMask) {
		this.ipv6AddressSubnetMask = ipv6AddressSubnetMask;
	}

	@JsonProperty("topology_type")
	public String getTopologyType() {
		return topologyType;
	}

	@JsonProperty("topology_type")
	public void setTopologyType(String topologyType) {
		this.topologyType = topologyType;
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