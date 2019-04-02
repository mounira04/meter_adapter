package com.meter.adapter.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MeterInformationDto implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516794983359308265L;
	
	private Long meterId;
	private String meterType;
	private String formatType;
	private String meterData;
}
