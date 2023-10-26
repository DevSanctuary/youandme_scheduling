package com.project.youandme_schedule.trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddClassForm {
    private Long trainerId;
    private String name;
    private Integer price;

}
