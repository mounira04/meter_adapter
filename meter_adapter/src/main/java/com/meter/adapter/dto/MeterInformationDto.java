package com.meter.adapter.dto;

import java.io.Serializable;

public class MeterInformationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5179367266908610985L;

	private Long meterId;
	private String meterType;
	private String formatType;
	private String meterData;

	public Long getMeterId() {
		return meterId;
	}

	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public String getMeterData() {
		return meterData;
	}

	public void setMeterData(String meterData) {
		this.meterData = meterData;
	}
	public MeterInformationDto(Long meterId, String meterType, String formatType, String meterData) {
		
		this.meterId = meterId;
		this.meterType = meterType;
		this.formatType = formatType;
		this.meterData = meterData;
	}

	public MeterInformationDto(Long meterId, String meterType) {
		
		this.meterId = meterId;
		this.meterType = meterType;
	}
	
}
