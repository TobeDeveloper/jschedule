package org.myan.jschedule.web.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header<T> {

    private String responseCode = "J-001";
    private String responseMessage = "Success";

    @JsonProperty("response_code")
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty("response_message")
    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
