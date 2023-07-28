package com.solvd.laba.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}${get_extension}", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/users/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.UNAUTHORIZED_401)
public class InvalidGetUserMethods extends AbstractApiMethodV2 {
	
    public InvalidGetUserMethods() {
    	replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
    }

    public InvalidGetUserMethods(String envVariable) {
    	replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
    	if (envVariable == "photo") {
    		replaceUrlPlaceholder("get_extension", Configuration.getRequired("photo_ext"));
    	} else if (envVariable == "topic") {
    		replaceUrlPlaceholder("get_extension", Configuration.getRequired("topic_ext"));
    	}
    }
}