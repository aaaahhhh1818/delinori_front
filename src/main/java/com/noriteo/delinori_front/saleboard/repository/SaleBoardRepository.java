package com.noriteo.delinori_front.saleboard.repository;

import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import com.noriteo.delinori_front.saleboard.repository.search.SaleBoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleBoardRepository extends JpaRepository<SaleBoard, Long>, SaleBoardSearch {


}
