package com.noriteo.delinori_front.saleboard.service;

import com.noriteo.delinori_front.saleboard.dto.PageRequestDTO;
import com.noriteo.delinori_front.saleboard.dto.PageResponseDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SaleBoardService {

    Long register(SaleBoardDTO saleBoardDTO);

    PageResponseDTO<SaleBoardDTO> getList(PageRequestDTO pageRequestDTO);

    SaleBoardDTO read(Long sno);

    void modify(SaleBoardDTO saleBoardDTO);

    void remove(Long sno);

}
