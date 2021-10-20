package com.noriteo.delinori_front.saleboard.repository;

import com.noriteo.delinori_front.saleboard.dto.SaleBoardDTO;
import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class SaleBoardRepoTests {

    @Autowired
    private SaleBoardRepository saleBoardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testDummies() {

        IntStream.rangeClosed(1,100).forEach(i -> {
            SaleBoard saleBoard = SaleBoard.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("user" + (i % 10))
                    .build();

            saleBoardRepository.save(saleBoard);
        });

    }

    @Test
    public void testSearch() {

        char[] typeArr = null;
        String keyword = null;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("sno").descending());

        Page<SaleBoard> result = saleBoardRepository.search(typeArr, keyword, pageable);

        result.get().forEach(saleBoard -> {

            log.info(saleBoard);
            log.info("-----------------");

            SaleBoardDTO saleBoardDTO = modelMapper.map(saleBoard, SaleBoardDTO.class);

            log.info(saleBoardDTO);

        });

    }

    @Test
    public void testEx1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("sno").descending());

        Page<Object[]> result = saleBoardRepository.ex1(pageable);

        log.info(result);

        result.get().forEach(element -> {

            Object[] arr = (Object[])element;

            log.info(Arrays.toString(arr));

        });

    }

}
