package com.noriteo.delinori_front.saleboard.repository;

import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import com.noriteo.delinori_front.saleboard.entity.SaleBoardReply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private SaleBoardReplyRepository saleBoardReplyRepository;

    @Test
    public void insert200() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            Long sno = (long) (200 - (i % 5));

            int replyCount = (i % 5);

            IntStream.rangeClosed(0, replyCount).forEach(j -> {

                SaleBoard saleBoard = SaleBoard.builder().sno(sno).build();

                SaleBoardReply saleBoardReply = SaleBoardReply.builder()
                        .reply("Reply....")
                        .replyer("replyer...")
                        .saleBoard(saleBoard)
                        .build();

                saleBoardReplyRepository.save(saleBoardReply);

            }); //inner loop

        }); //outet loop
    }

    @Test
    public void testListOfBoard() {

        Pageable pageable =
                PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<SaleBoardReply> result = saleBoardReplyRepository.getListBySno(197L, pageable);

        log.info(result.getTotalElements());

        result.get().forEach(reply -> log.info(reply));

    }

}
