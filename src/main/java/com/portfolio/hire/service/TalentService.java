package com.portfolio.hire.service;

import com.portfolio.hire.dao.entity.Talent;
import com.portfolio.hire.dao.repository.TalentRepository;
import com.portfolio.hire.exception.NotFoundException;
import com.portfolio.hire.model.request.TalentCreateRequest;
import com.portfolio.hire.model.response.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.portfolio.hire.mapper.TalentBuilder.toTalent;
import static com.portfolio.hire.model.constants.ExceptionConstants.TALENT_NOT_FOUND_CODE;
import static com.portfolio.hire.model.constants.ExceptionConstants.TALENT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class TalentService {
    private final TalentRepository talentRepository;

    public GenericResponseDto<String> createTalent(TalentCreateRequest talentCreateRequest) {
        return GenericResponseDto.<String>builder()
                .data(talentRepository.save(toTalent(talentCreateRequest)).getId())
                .build();
    }

    public Talent getTalent(String id) {
        return talentRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        String.format(TALENT_NOT_FOUND_MESSAGE, id), TALENT_NOT_FOUND_CODE
                )
        );
    }
}
