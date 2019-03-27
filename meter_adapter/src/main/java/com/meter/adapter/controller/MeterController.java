package com.meter.adapter.controller;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.meter.adapter.exception.BusinessResourceException;
import com.meter.adapter.model.MeterInformation;
import com.meter.adapter.service.ConverterData;
import com.meter.adapter.service.MeterService;

@Controller
public class MeterController {

	private static final Logger logger = LoggerFactory.getLogger(MeterController.class);

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Queue queue;
	@Autowired
	private MeterService meterService;
	@Autowired
	private ConverterData converterData;

	private String path;

	@PostMapping(value = "/meter")
	
	public ResponseEntity<MeterInformation> saveMeter(@RequestBody MeterInformation meter) {

		MeterInformation meterResult = meterService.getMeterInformationById(meter.getMeterId());
		if (meterResult == null) {
			logger.debug("Le meter " + meter.getMeterId() + " n'existe pas");
			throw new BusinessResourceException("Meter not existant", "", HttpStatus.NOT_FOUND);
		}

		if (meter.getFormatType() != null && meter.getMeterData() != null)

		{
			path = converterData.ConvertToXmlFile(meter.getMeterData(), meter.getFormatType());
			jmsTemplate.convertAndSend(queue, path);
		}
		return null;

	}
}
