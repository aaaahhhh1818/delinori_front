package com.noriteo.delinori_front.saleboard.service;

import com.noriteo.delinori_front.saleboard.dto.PageRequestDTO;
import com.noriteo.delinori_front.saleboard.dto.PageResponseDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardReplyDTO;
import com.noriteo.delinori_front.saleboard.entity.SaleBoardReply;
import com.noriteo.delinori_front.saleboard.repository.SaleBoardReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SaleBoardReplyServiceImpl implements SaleBoardReplyService {

    private final ModelMapper modelMapper;

    private final SaleBoardReplyRepository saleBoardReplyRepository;

    @Override
    public PageResponseDTO<SaleBoardReplyDTO> getListOfBoard(Long sno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = null;

        if(pageRequestDTO.getPage() == -1) {
            int lastPage = calcLastPage(sno, pageRequestDTO.getSize());
            if(lastPage <= 0) {
                lastPage = 1;
            }
            pageRequestDTO.setPage(lastPage);
        }

        pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize());

        Page<SaleBoardReply> result = saleBoardReplyRepository.getListBySno(sno, pageable);

        List<SaleBoardReplyDTO> dtoList = result.get()
                .map(saleBoardReply -> modelMapper.map(saleBoardReply, SaleBoardReplyDTO.class))
                .collect(Collectors.toList());

        return new PageResponseDTO<>(pageRequestDTO, (int) result.getTotalElements(), dtoList);
    }

    private int calcLastPage(Long sno, double size) {

        int count = saleBoardReplyRepository.getReplyCountOfBoard(sno);
        int lastPage = (int)(Math.ceil(count/size));

        return lastPage;

    }
}
