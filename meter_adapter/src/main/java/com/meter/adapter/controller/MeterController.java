package com.meter.adapter.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meter.adapter.conveter.ConvertData;
import com.meter.adapter.model.MeterInformation;
import com.meter.adapter.service.MeterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/meter")
@Slf4j
public class MeterController {

//	private static final Logger logger = LoggerFactory.getLogger(MeterController.class);

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Queue queue;
	@Autowired
	private MeterService meterService;
	@Autowired
	private ConvertData converterData;

	private String path;

	@PostMapping
	public String saveMeter(MeterInformation meter) {

//		MeterInformation meterResult = meterService.getMeterInformationById(meter.getMeterId());
//		if (meterResult == null) {
//			logger.debug("Le meter " + meter.getMeterId() + " n'existe pas");
//			throw new BusinessResourceException("Meter not existant", "", HttpStatus.NOT_FOUND);
//		}

		
			System.out.println("first step");
			path = converterData.ConvertToXmlFile(meter.getMeterData(), meter.getFormatType());
			System.out.println("path");
			jmsTemplate.convertAndSend(queue, path);
		
		log.info("hello");
		return "HI";

	}
}
