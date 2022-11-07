package com.portfolio.hire.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TalentCreateRequest {
    private String firstName;
    private String lastName;
    private int age;
}
