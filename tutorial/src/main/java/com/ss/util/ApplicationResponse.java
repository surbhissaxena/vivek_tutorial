package com.ss.util;

import java.util.List;
import java.util.Map;

public class ApplicationResponse {
	  String message;
      Map<String , List<Object>> Data;
      byte  operationCode;
      Boolean dataAvailable;
      
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Map<String, List<Object>> getData() {
			return Data;
		}
		public void setData(Map<String, List<Object>> data) {
			Data = data;
		}
		public byte getOperationCode() {
			return operationCode;
		}
		public void setOperationCode(byte operationCode) {
			this.operationCode = operationCode;
		}
		public Boolean getDataAvailable() {
			return dataAvailable;
		}
		public void setDataAvailable(Boolean dataAvailable) {
			this.dataAvailable = dataAvailable;
		}
}
