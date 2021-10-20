package com.noriteo.delinori_front.saleboard.service;

import com.noriteo.delinori_front.saleboard.dto.PageRequestDTO;
import com.noriteo.delinori_front.saleboard.dto.PageResponseDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardReplyDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SaleBoardReplyService {

    PageResponseDTO<SaleBoardReplyDTO> getListOfBoard(Long sno, PageRequestDTO pageRequestDTO);

}
