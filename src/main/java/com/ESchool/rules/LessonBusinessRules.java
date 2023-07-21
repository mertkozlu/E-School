package com.ESchool.rules;

import com.ESchool.dataAccess.LessonRepository;
import com.ESchool.dtos.requests.AddLessonRequest;
import com.ESchool.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonBusinessRules {

    private final LessonRepository lessonRepository;

    public void existsByLessonName(String lessonName) {
        if (this.lessonRepository.existsByLessonName(lessonName)) {
            throw new BusinessException("LessonName already exists");
        }
    }

    public void existsByLessonId(Long lessonId) {
        if (this.lessonRepository.existsByLessonId(lessonId)) {
            throw new BusinessException("LessonId already exists");
        }
    }

    public boolean validateRequest(AddLessonRequest addLessonRequest) {
        boolean isSuccess = true;

        if (StringUtils.isEmpty(addLessonRequest.getLessonName())) {
            isSuccess = false;
        }
        return isSuccess;
    }
}
