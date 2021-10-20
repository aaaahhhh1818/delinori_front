package com.noriteo.delinori_front.saleboard.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "saleBoard")
@Table(name = "reply_saleboard")
public class SaleBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reply;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sno")
    private SaleBoard saleBoard;

    @CreationTimestamp
    private LocalDateTime replyDate;

    public void setText(String text){
        this.reply = text;
    }

}
