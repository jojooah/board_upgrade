package com.example.board_upgrade.dto;

import com.example.board_upgrade.constant.Category;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BoardFormDTO {
    String title;
    String content;
    Category category;

}
