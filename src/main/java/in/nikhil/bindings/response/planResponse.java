package in.nikhil.bindings.response;

import lombok.Data;

@Data
public class planResponse {
	private Integer planId;
	private String planName;
	private String planHolderName;
	private Long planHolderSsn;
	private String planStatus;
}
