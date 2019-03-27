package com.meter.adapter.service;

import java.util.Collection;

import com.meter.adapter.model.MeterInformation;

public interface MeterService {

	MeterInformation getMeterInformationById(Long id);

	void deleteMeter(MeterInformation meterInformation);

	MeterInformation createMeter(MeterInformation meterInformation);

	MeterInformation updateMeter(MeterInformation meterInformation);
	
	Collection<MeterInformation> getAllMeterInformation();

} 