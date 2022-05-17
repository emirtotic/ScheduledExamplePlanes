package com.scheduled.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Plane {

    @Id
    private long id;
    private String companyName;
    private String planeName;
    private int space;
    private int bookedSpace;
}
