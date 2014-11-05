package com.zillabyte.motherbrain.flow.config;

import java.util.Map;

import com.zillabyte.motherbrain.utils.JSONUtil;

import net.sf.json.JSONObject;

/*************************
 * Flow Config
 *************************/
public class FlowConfig extends UserConfig {
  
  private static final long serialVersionUID = 3630661150096942482L;

  public String getAuthToken() {
    return get("auth_token");
  }
  
  public String getFlowId() {
    return get("flow_id").toString();
  }
  
  public Integer getFlowVersion() {
    return get("flow_version");
  }
  
  public Integer getUserId() {
    if (get("user_id") == null) { 
      return null;
    } else {
      return Integer.valueOf(get("user_id").toString());
    }
  }
  
  public static FlowConfig createEmpty() {
    return new FlowConfig();
  }
  
  public static FlowConfig createMock() {
    return createEmpty()
        .set("auth_token", "mock_auth_token");
    // Config for kafka?
  }

  public static FlowConfig createFromJSON(String flowConfigJSON) {
    JSONObject obj = JSONUtil.parseObj(flowConfigJSON);
    return new FlowConfig().setAll(obj);
  }
  
  public static FlowConfig createFromJSON(JSONObject obj) {
    if (obj == null) {
      return FlowConfig.createEmpty();
    } else {
      return new FlowConfig().setAll(obj);
    }
  }

  public static FlowConfig createMock(String flowId, Integer version, Integer userId) {
    return createMock()
        .set("flow_id", flowId)
        .set("flow_version", version)
        .set("user_id", userId);
  }

  public static FlowConfig createMock(String flowId) {
    return createMock(flowId, 1, 1);
  }

  public boolean getShouldMerge() {
    if (get("output_format","").equalsIgnoreCase("merge")) {
      return true;
    } else if (get("merge", "").equalsIgnoreCase("true")) {
      return true;
    } else {
      return false;
    }
  }
  
  
  public FlowConfig mergeWith(Map<String, Object> conf) {
    FlowConfig c = new FlowConfig();
    c._settings.putAll(this._settings);
    c._settings.putAll(conf);
    return c;
  }
  
  

  
  
}