package com.portfolio.hire.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestDto {
    @NotNull
    private String companyId;

    @Future
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date start;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date   end;
}
