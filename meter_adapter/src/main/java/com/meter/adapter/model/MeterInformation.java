package com.meter.adapter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "METER_INFO")
public class MeterInformation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1089033427086442511L;
	@Id
	@Column(name = "METER_ID", updatable = false, nullable = false)
	private Long meterId;
	@Column(name = "METER_TYPE", updatable = false, nullable = false)
	private String meterType;
	@Column(name = "FORMAT_TYPE")
	private String formatType;
	@Column(name = "METER_DATA")
	private String meterData;

}
