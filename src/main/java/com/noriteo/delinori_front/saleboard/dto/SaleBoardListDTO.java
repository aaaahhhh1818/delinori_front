package com.noriteo.delinori_front.saleboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleBoardListDTO {

    private Long sno;

    private String title;

    private String writer;

    private LocalDateTime regDate;

    private long replyCount;

}
