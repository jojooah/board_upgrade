package com.example.board_upgrade.entity;

import com.example.board_upgrade.constant.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @Lob
    private String content;
    private LocalDateTime updateTime;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "like_")
    private int like;


}