package com.meter.adapter.service.impl;

import java.util.Collection;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meter.adapter.dao.MeterRepository;
import com.meter.adapter.model.MeterInformation;
import com.meter.adapter.service.MeterService;

@Service(value="MeterService")
public class MeterServiceImpl implements MeterService{

	@Autowired
	private MeterRepository meterRepository;
	
	
	@Override
	public MeterInformation getMeterInformationById(Long id) {		
		
		return meterRepository.findOne(id);
	}

	@Override
	public void deleteMeter(MeterInformation meterInformation) {
		meterRepository.delete(meterInformation);
		
	}

	@Override
	public MeterInformation createMeter(MeterInformation meterInformation) {
		
		return meterRepository.save(meterInformation);
	}

	@Override
	@Transactional(readOnly=false)
	public MeterInformation updateMeter(MeterInformation meterInformation) {
		
		return meterRepository.save(meterInformation);
	}

	@Override
	public Collection<MeterInformation> getAllMeterInformation() {
		
		return IteratorUtils.toList(meterRepository.findAll().iterator());
	}

}
