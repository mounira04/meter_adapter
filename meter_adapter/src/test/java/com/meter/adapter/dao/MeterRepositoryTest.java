package com.meter.adapter.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.meter.adapter.model.MeterInformation;


@RunWith(SpringRunner.class)
@DataJpaTest

public class MeterRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;    
    @Autowired
    private MeterRepository meterRepository;    
    MeterInformation meter = new MeterInformation(1L, "EAU", "F1", "Trame Eau");
    
    @Before
    public void setup(){
        entityManager.persist(meter);
        entityManager.flush();
    }
    @Test
    public void testFindAllUsers() {
        List<MeterInformation> meters = meterRepository.findAll();
        assertThat(1, is(meters.size()));
    }
    
    @Test
    public void testSaveMeter(){
    	MeterInformation meter = new MeterInformation(2L, "GAZ", "F1", "Trame Eau");
    	MeterInformation meterSaved =  meterRepository.save(meter);
        assertNotNull(meterSaved.getMeterId());
        assertThat("GAZ", is(meterSaved.getMeterType()));
    }
    @Test
    public void testFindByLogin() {
    	Optional<MeterInformation> meterFromDB = meterRepository.findById(1L)     ;
        assertThat(true,is(meterFromDB.isPresent()));
    }
    
    @Test
    public void testDeleteMeter(){
    	meterRepository.deleteById(meter.getMeterId());
    	Optional<MeterInformation> meterFromDB = meterRepository.findById(meter.getMeterId());
    	 assertThat(false,is(meterFromDB.isPresent()));
    }
    
    @Test
    public void testUpdateMeter() {//Test si le compte utilisateur est désactivé
    	Optional<MeterInformation> meterToUpdateList = meterRepository.findById(1L);
    	MeterInformation m=meterToUpdateList.get();
    	m.setMeterData("trame modifié");
        meterRepository.save(m);       
        Optional<MeterInformation> meterToUpdateList2 = meterRepository.findById(1L);
    	MeterInformation result=meterToUpdateList2.get();
      
        assertNotNull(result);
        assertThat("trame modifié", is(result.getMeterData()));
    }        
}