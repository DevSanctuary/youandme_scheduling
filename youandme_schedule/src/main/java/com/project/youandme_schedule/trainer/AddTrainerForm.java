package com.project.youandme_schedule.trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTrainerForm {

    private String name;
    private String description;
    private List<AddClassForm> addClassFormList;
}
