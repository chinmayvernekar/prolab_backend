package com.prolab.termCondition;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TermsConditionServiceImpl implements TermsConditionService {
	
	
	@Autowired
	TermsConditionRepo termsConditionRepo;
	
	
	@PostConstruct
	private void addDefaultTermsCondition() {
		TermsConditionModel conditionModel = new TermsConditionModel();
		conditionModel.setPrices("Ex our works in Mumbai.");
		conditionModel.setTax("GST will be charged @ 18%.");
		conditionModel.setDelivery("In about 0 – 2 weeks after receipt of your order.");
		conditionModel.setFreight("INCLUDED.");
		conditionModel.setInstallation("EXTRA IF NEEDED.");
		conditionModel.setApproval("At our office in Mumbai.");
		conditionModel.setPayment("100% ADVANCE WITH PO.");
		conditionModel.setWarranty("The instrument is warranted for satisfactory service of `3 Year (METER ONLY)`."
				+ " NO WARRANTY ON CONSUMABLES LIKE: ELECTRODES, BATTERIES,ADAPTORS ETC.");
	}
	
	
	
	
	@Override
	public ResponseEntity<List<TermsConditionModel>> getDefaultTermsCondition() {
		// TODO Auto-generated method stub
		
		return ResponseEntity.ok(termsConditionRepo.findAll());
	}

}
