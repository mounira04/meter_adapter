package com.meter.adapter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.meter.adapter.dto.MeterInformationDto;

@Entity
@Table(name = "METER_INFO")
public class MeterInformation implements Serializable {
	@Id
	@Column(name = "METER_ID", updatable = false, nullable = false)
	private Long meterId;
	@Column(name = "METER_TYPE", updatable = false, nullable = false)
	private String meterType;
	@Column(name = "FORMAT_TYPE")
	private String formatType;
	@Column(name = "METER_DATA")
	private String meterData;

	public Long getMeterId() {
		return meterId;
	}

	public MeterInformation(Long meterId, String meterType, String formatType, String meterData) {
		
		this.meterId = meterId;
		this.meterType = meterType;
		this.formatType = formatType;
		this.meterData = meterData;
	}

	public MeterInformation(MeterInformationDto meterInformationDto)
	{
		this.meterId = meterInformationDto.getMeterId();
		this.meterType = meterInformationDto.getMeterType();
		this.formatType = meterInformationDto.getFormatType();
		this.meterData = meterInformationDto.getMeterData();
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

}
