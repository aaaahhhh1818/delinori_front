package com.noriteo.delinori_front.saleboard.repository;

import com.noriteo.delinori_front.saleboard.entity.SaleBoardReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleBoardReplyRepository extends JpaRepository<SaleBoardReply, Long> {

    List<SaleBoardReply> findSaleBoardReplyBySaleBoard_SnoOrderByRno(Long sno);

    @Query("select r from SaleBoardReply r where r.saleBoard.sno = :sno")
    Page<SaleBoardReply> getListBySno(Long sno, Pageable pageable);

    @Query("select count(r) from SaleBoardReply r where r.saleBoard.sno = :sno")
    int getReplyCountOfBoard(Long sno);

}
