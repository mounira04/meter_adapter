package com.meter.adapter.conveter;

import org.springframework.stereotype.Service;

@Service
public class ConvertData {

	
	public String convertToXmlFile(String meterData,String formatType) {
		
		
		return meterData.concat(formatType);
	}

}
