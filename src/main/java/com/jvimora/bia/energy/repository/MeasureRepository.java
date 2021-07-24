package com.jvimora.bia.energy.repository;

import com.jvimora.bia.energy.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {

    List<Measure> findByDeviceSN(String deviceSN);
    List<Measure> findByTimestampBetweenAndDeviceSN(Date init, Date end, String deviceSN);

}
