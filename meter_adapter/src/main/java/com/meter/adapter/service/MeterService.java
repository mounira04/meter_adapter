package com.meter.adapter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meter.adapter.dao.MeterRepository;
import com.meter.adapter.dto.MeterInformationDto;
import com.meter.adapter.exception.MeterNotFoundException;
import com.meter.adapter.model.MeterInformation;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MeterService {
	@Autowired
	private MeterRepository meterRepository;

	public MeterService(MeterRepository meterRepository) {
		this.meterRepository = meterRepository;
	}

	public MeterService() {
		super();
	}

	@Transactional(readOnly = true)
	public MeterInformation getMeterById(Long id)  throws MeterNotFoundException{
		Optional<MeterInformation> meters = meterRepository.findById(id);
		if (meters.isPresent()) {
			return meters.get();
		}
		return null;
	}
@Transactional(readOnly = true)
	public MeterInformation createMeter(MeterInformationDto meterInfo) {
		MeterInformation meter = new MeterInformation();
		try {
			if (meterInfo.getMeterId() != null) {
				throw new MeterNotFoundException("A new meter cannot already have an ID");
			}
			
			meter.setFormatType(meterInfo.getFormatType());
			meter.setMeterData(meterInfo.getMeterData());
			meter.setMeterType(meterInfo.getMeterType());
			return meterRepository.save(meter);
		} catch (MeterNotFoundException ex) {
			log.info(ex.getMessage());
			
		}
		return meter;
	}

	@Transactional(readOnly = true)
	public MeterInformation updateMeter(MeterInformationDto meterInfo) {
		MeterInformation meter = null;
		try {
			if (meterInfo.getMeterId() == null) {
				throw new MeterNotFoundException("Please indicate the meter ID");
			}
			meter = getMeterById(meterInfo.getMeterId());
			meter.setFormatType(meterInfo.getFormatType());
			meter.setMeterData(meterInfo.getMeterData());
			meter.setMeterType(meterInfo.getMeterType());
			meterRepository.saveAndFlush(meter);

		} catch (MeterNotFoundException ex) {
			ex.getMessage();
			log.info(ex.getMessage());
			
		}
		return meter;
	}

	@Transactional(readOnly = true)
	public List<MeterInformation> getAllMeterInformation() {
		return meterRepository.findAll();
	}

	public void deleteMeter(Long id) {
		try {
			meterRepository.deleteById(id);
		} catch (Exception ex) {
			log.info(ex.getMessage());
			throw new MeterNotFoundException("Error to delete meter: " + id);

		}

	}

}