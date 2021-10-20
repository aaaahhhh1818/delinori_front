package com.noriteo.delinori_front.saleboard.controller;

import com.noriteo.delinori_front.saleboard.dto.PageRequestDTO;
import com.noriteo.delinori_front.saleboard.dto.PageResponseDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardReplyDTO;
import com.noriteo.delinori_front.saleboard.service.SaleBoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/saleboard/replies")
public class SaleBoardReplyController {

    private final SaleBoardReplyService saleBoardReplyService;

    @GetMapping("/list/{sno}")
    public PageResponseDTO<SaleBoardReplyDTO> getListOfBoard(@PathVariable("sno") Long sno, PageRequestDTO pageRequestDTO) {

        return saleBoardReplyService.getListOfBoard(sno, pageRequestDTO);

    }

}
