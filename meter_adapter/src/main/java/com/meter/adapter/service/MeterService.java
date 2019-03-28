package com.meter.adapter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meter.adapter.dao.MeterRepository;
import com.meter.adapter.exception.BadRequestAlertException;
import com.meter.adapter.exception.ResourceNotFoundException;
import com.meter.adapter.model.MeterInformation;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MeterService {
	
	private final MeterRepository meterRepository;

	public MeterService(MeterRepository meterRepository) {
		this.meterRepository = meterRepository;
	}

	@Transactional(readOnly = true)
	public Optional<MeterInformation> getMeterById(Long id) {
		return meterRepository.findById(id);
	}

	public MeterInformation createMeter(MeterInformation meterInformation) {
		if (meterInformation.getMeterId() != null) {
			throw new BadRequestAlertException("A new meter cannot already have an ID", "meter", "idexists");
		}
		return meterRepository.save(meterInformation);
	}

	public MeterInformation updateMeter(MeterInformation meterInfo) {
		if (meterInfo.getMeterId() == null) {
			throw new BadRequestAlertException("Please indicate the meter ID", "meter", "idnotfound");
		}
		return meterRepository.findById(meterInfo.getMeterId()).map(meter -> {
			meter.setMeterData(meterInfo.getMeterData());
			meter.setFormatType(meterInfo.getFormatType());
			meter.setMeterType(meterInfo.getMeterType());
			return meter;
		}).orElseThrow(() -> new ResourceNotFoundException(
				"There is not exist a meter with the Id " + meterInfo.getMeterId()));
	}

	@Transactional(readOnly = true)
	public List<MeterInformation> getAllMeterInformation() {
		return meterRepository.findAll();
	}

	public void deleteMeter(Long id) {
		meterRepository.findById(id).ifPresent(meter -> {
			meterRepository.delete(meter);
			log.debug("Deleted meter: {}", meter);
		});
	}

}