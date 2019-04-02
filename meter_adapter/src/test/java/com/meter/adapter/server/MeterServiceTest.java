package com.meter.adapter.server;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.meter.adapter.dao.MeterRepository;
import com.meter.adapter.model.MeterInformation;
import com.meter.adapter.service.MeterService;

@RunWith(SpringRunner.class)
public class MeterServiceTest {
 
    @TestConfiguration //création des beans nécessaires pour les tests
    static class MeterServiceTestContextConfiguration {
        
        @Bean//bean de service
        public MeterService meterService () {
            return new MeterService();
        }
       
    }
 
    @Autowired
    private MeterService meterService;
 
    @MockBean //création d'un mockBean pour meterRepository
    private MeterRepository meterRepository;
    
    MeterInformation meter = new MeterInformation(2L,"EAU","F1", "TEST SERVICE");
    
    @Test
    
    public void testFindAllMeters() throws Exception {
    	MeterInformation meter = new MeterInformation(1L,"GAZ","F2", "TEST SERVICE");
        
        List<MeterInformation> allMeter = Arrays.asList(meter);           
        Mockito.when(meterRepository.findAll()).thenReturn(allMeter);
        Collection<MeterInformation> meters = meterService.getAllMeterInformation();
        assertNotNull(meters);
        assertEquals(meters, allMeter);
        assertEquals(meters.size(), allMeter.size());
        verify(meterRepository).findAll();
    }
    
    @Test
    public void testSaveMeter() throws Exception {
    	MeterInformation meter = new MeterInformation(2L,"GAZ","F2", "TEST SERVICE");
        MeterInformation meterMock = new MeterInformation();
        Mockito.when(meterRepository.save((meter))).thenReturn(meterMock);
        assertNotNull(meterMock);
        
      
       
    }

   
    @Test
    public void testDelete() throws Exception {
        MeterInformation meter = new MeterInformation(2L,"EAU", "password", "YYY");
//        MeterInformation meterMock = new MeterInformation(1L,"ELEC", "password","XXX");
//        Mockito.when(meterRepository.save((meter))).thenReturn(meterMock);
//        MeterInformation meterSaved = meterService.updateMeter(meter);
        assertNotNull(meter);       
        meterService.deleteMeter(meter.getMeterId());
        verify(meterRepository).delete((MeterInformation) any(Long.class));
    }
//    
//    @Test
//    public void testUpdateMeter() throws Exception {
//        Meter meterToUpdate = new Meter(1L,"Dupont", "password", 1);
//        Meter meterUpdated = new Meter(1L,"Paul", "password", 1);
//        Mockito.when(meterRepository.save((meterToUpdate))).thenReturn(meterUpdated);
//        Meter meterFromDB = meterService.saveOrUpdateMeter(meterToUpdate);
//        assertNotNull(meterFromDB);
//        assertEquals(meterUpdated.getLogin(), meterFromDB.getLogin());
//        verify(meterRepository).save(any(Meter.class));        
//    }    
}
