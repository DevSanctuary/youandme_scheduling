package com.project.youandme_schedule.trainer;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainerId;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private List<Class> classes = new ArrayList<>();

    public static Trainer of(Long trainerId,AddTrainerForm form) {
        return Trainer.builder()
                .trainerId(trainerId)
                .name(form.getName())
                .description(form.getDescription())
                .classes(form.getAddClassFormList().stream()
                        .map(piFrom->Class.of(trainerId,piFrom)).collect(Collectors.toList()))
                .build();
    }
}
