package com.java.qa.sccm.base;

public interface APIUrl {

	String baseUrl = "http://173.37.21.174";

	String LoginPostUrl = "/api/v1/login";
	String postStep1 = "api/v1/servicedeploy/step1";

	String postLoginUrl = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/login";

	String postCreateCustomerUrl = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/customers";

	String getCustomerDataUrl = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/customers?customer_id=";

	String putUpdateCustomerUrl = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/customers?customer_id=";
	
	String deleteCustomerUrl ="http://173.37.21.174/SCCM/sccm_api/web/api/v1/customers?customer_id=";

	String getDevicesLatlongPincode = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/device";

	String getDeviceNeidVersion = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/device?neid=";

	String postServiceDeployStep1 = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/servicedeploy/step1";

	String postServiceDeployStep2 = "http://173.37.21.174/SCCM/sccm_api/web/api/v1/servicedeploy/step2";
}
