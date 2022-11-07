package com.portfolio.hire.service;

import com.portfolio.hire.dao.repository.ShiftRepository;
import com.portfolio.hire.exception.LessThanOneShiftException;
import com.portfolio.hire.exception.NotFoundException;
import com.portfolio.hire.model.response.GenericResponseDto;
import com.portfolio.hire.model.response.ShiftResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.portfolio.hire.mapper.ShiftResponseDtoBuilder.toShiftResponse;
import static com.portfolio.hire.model.constants.ExceptionConstants.LESS_THAN_ONE_SHIFT_CODE;
import static com.portfolio.hire.model.constants.ExceptionConstants.LESS_THAN_ONE_SHIFT_MESSAGE;
import static com.portfolio.hire.model.constants.ExceptionConstants.SHIFT_NOT_FOUND_CODE;
import static com.portfolio.hire.model.constants.ExceptionConstants.SHIFT_NOT_FOUND_MESSAGE;
import static com.portfolio.hire.model.enums.Status.ACTIVE;
import static com.portfolio.hire.model.enums.Status.CANCELLED;

@Service
@Transactional
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;

    private final TalentService talentService;

    public GenericResponseDto<List<ShiftResponseDto>> getShifts(String jobId) {
        var shiftResponses = shiftRepository.findAllByJobIdAndStatus(jobId, ACTIVE).stream()
                .map(shift -> toShiftResponse(jobId, shift))
                .collect(Collectors.toList());
        return GenericResponseDto.<List<ShiftResponseDto>>builder()
                .data(shiftResponses)
                .build();
    }

    public void bookTalent(String talentId, String shiftId) {
        var talent = talentService.getTalent(talentId);

        shiftRepository.findByIdAndStatus(shiftId, ACTIVE)
                .map(shift -> shiftRepository.save(shift.setTalent(talent)));
    }

    public void cancelShift(String id) {
        var shift = shiftRepository.findByIdAndStatus(id, ACTIVE).orElseThrow(
                () -> new NotFoundException(
                        String.format(SHIFT_NOT_FOUND_MESSAGE, id), SHIFT_NOT_FOUND_CODE
                )
        );

        var genericShiftResponse = getShifts(shift.getJob().getId());

        if (genericShiftResponse.getData().size() <= 1)
            throw new LessThanOneShiftException(LESS_THAN_ONE_SHIFT_MESSAGE, LESS_THAN_ONE_SHIFT_CODE);

        shiftRepository.save(
                shift.setStatus(CANCELLED)
        );
    }

    public void cancelTalentAllShifts(String talentId) {

        var shiftList = shiftRepository.findAllByTalentIdAndStatus(talentId, ACTIVE);

        shiftList.forEach(
                shift -> cancelShift(shift.getId())
        );
    }
}
