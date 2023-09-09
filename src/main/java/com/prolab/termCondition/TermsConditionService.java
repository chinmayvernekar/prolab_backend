package com.prolab.termCondition;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface TermsConditionService {
	
	public  ResponseEntity<List<TermsConditionModel>> getDefaultTermsCondition();
}
