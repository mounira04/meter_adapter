package com.meter.adapter.controller;

import java.util.List;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meter.adapter.conveter.ConvertData;
import com.meter.adapter.dto.MeterInformationDto;
import com.meter.adapter.exception.MeterNotFoundException;
import com.meter.adapter.model.MeterInformation;
import com.meter.adapter.service.MeterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/meters")
@Slf4j
public class MeterController {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Queue queue;
	@Autowired
	private MeterService meterService;
	@Autowired
	private ConvertData converterData;

	@GetMapping
	public ResponseEntity<List<MeterInformation>> findAll() {
		return ResponseEntity.ok(meterService.getAllMeterInformation());
	}

	@GetMapping("/meters/convert")
	public String convetDataMeter(MeterInformationDto meter) {
		
		MeterInformation m= meterService.getMeterById(meter.getMeterId());
		if(m==null)
		{
			throw new MeterNotFoundException(meter.getMeterId().toString());		}
		
		String path = converterData.convertToXmlFile(meter.getMeterData(), meter.getFormatType());
		
		jmsTemplate.convertAndSend(queue, path);

		log.info("hello");
		return path;

	}
}
