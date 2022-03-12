package com.example.board_upgrade.dto;

import com.example.board_upgrade.constant.Category;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BoardFormDTO {
    Long id;
    Long memberId;
    String title;
    String content;
    Category category;

}
