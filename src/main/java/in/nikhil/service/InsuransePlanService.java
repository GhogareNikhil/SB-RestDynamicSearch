package in.nikhil.service;

import java.util.List;

import in.nikhil.bindings.request.SearchRequest;
import in.nikhil.bindings.response.planResponse;

public interface InsuransePlanService {
  
	public List<planResponse> searchPlans(SearchRequest request);
	public List<String> getUniquePlanName();
	public List<String> getUniquePlanStatus();
}
