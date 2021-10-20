package com.noriteo.delinori_front.saleboard.service;

import com.noriteo.delinori_front.saleboard.dto.PageRequestDTO;
import com.noriteo.delinori_front.saleboard.dto.PageResponseDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardListDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class SaleBoardServiceTests {

    @Autowired
    private SaleBoardService saleBoardService;

    @Test
    public void testRegister() {

        IntStream.rangeClosed(1, 200).forEach(i -> {
            SaleBoardDTO saleBoardDTO = SaleBoardDTO.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer("user" + i)
                    .build();

            Long sno = saleBoardService.register(saleBoardDTO);
            log.info("sno: " + sno);
        });

    }

}
