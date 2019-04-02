package com.meter.adapter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meter.adapter.model.MeterInformation;

public interface MeterRepository extends JpaRepository<MeterInformation, Long>
{

	

}
