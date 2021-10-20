package com.noriteo.delinori_front.saleboard.repository;

import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import com.noriteo.delinori_front.saleboard.repository.search.SaleBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleBoardRepository extends JpaRepository<SaleBoard, Long>, SaleBoardSearch {

    @Query("select s.sno, s.title, s.writer, count(r) from SaleBoard s left join SaleBoardReply r on r.saleBoard = s group by s")
    Page<Object[]> ex1(Pageable pageable);

}
