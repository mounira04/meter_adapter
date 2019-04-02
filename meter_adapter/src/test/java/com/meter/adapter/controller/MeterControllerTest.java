package com.meter.adapter.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import javax.jms.Queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.meter.adapter.conveter.ConvertData;
import com.meter.adapter.exception.MeterNotFoundException;
import com.meter.adapter.model.MeterInformation;
import com.meter.adapter.service.MeterService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MeterControllerTest {
   
 
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private MeterService meterService;
   @MockBean
	private JmsTemplate jmsTemplate;
   @MockBean
	private Queue queue;
   @MockBean
   private ConvertData convertData;


   
   @Test
   public void findAll() throws Exception {
	   MeterInformation meter = new MeterInformation();
	   meter.setMeterId(1L);
	   meter.setFormatType("F1");
	   meter.setMeterType("EAU");
	   meter.setMeterData("Data");

       List<MeterInformation> meters = Arrays.asList(meter);
       given(meterService.getAllMeterInformation()).willReturn(meters);

       this.mockMvc.perform(get("/meters"))
               .andExpect(status().isOk())
               .andExpect(content().json("[{'meterId'=1, 'meterType'=EAU, 'formatType'=F1, 'meterData'=Data}]"));
   }
   
   @Test
   public void getPth() throws MeterNotFoundException {
	   String path="";
	   MeterInformation meter = new MeterInformation();
	   meter.setMeterId(1L);
	   meter.setFormatType("F1");
	   meter.setMeterType("EAU");
	   meter.setMeterData("Data");

      
       
       given(convertData.convertToXmlFile(meter.getMeterData(), meter.getFormatType())).willReturn(path);

       try {
		this.mockMvc.perform(get("/meters/convert"))
		           .andExpect(status().isOk());
	} catch (Exception e) {
		
		e.printStackTrace();
	}
   }

}