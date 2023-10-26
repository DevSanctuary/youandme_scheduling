package com.project.youandme_schedule.trainer;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Class extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long trainerId;

    private String name;

    private Integer price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private Trainer trainer;

    public static Class of(Long trainerId,AddClassForm form){
        return Class.builder()
                .trainerId(trainerId)
                .name(form.getName())
                .price(form.getPrice())
                .build();
    }

}
