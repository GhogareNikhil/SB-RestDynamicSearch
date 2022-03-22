package in.nikhil.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import in.nikhil.bindings.request.SearchRequest;
import in.nikhil.bindings.response.planResponse;
import in.nikhil.entity.InsuransePlanEntity;
import in.nikhil.repository.InsuransePlanRepository;

@Service
public class InsuransePlanServiceImpl implements InsuransePlanService {

	@Autowired
	private InsuransePlanRepository repository;

	@Override
	public List<planResponse> searchPlans(SearchRequest request) {
		InsuransePlanEntity entity = new InsuransePlanEntity();
		if (request!=null && request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request!=null && request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		Example<InsuransePlanEntity> of = Example.of(entity);

		List<InsuransePlanEntity> findAll = repository.findAll(of);
		List<planResponse> plans = new ArrayList<>();
		for (InsuransePlanEntity plan : findAll) {
			planResponse presp = new planResponse();
			BeanUtils.copyProperties(plan, presp);
			plans.add(presp);
		}
		return plans;
	}

	@Override
	public List<String> getUniquePlanName() {
		return repository.getplanName();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return repository.getplanStatus();
	}

}
