package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 1단계 : Create - 일정 작성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return responseDto;
    }

    // 2단계 : Read - 식별자로 선택한 일정 조회
    public ScheduleResponseDto getSchedule(Long id) {
        return new ScheduleResponseDto(findSchedule(id));
    }

    // 3단계 : ReadAll - 일정 전체조회 : 작성일 기준 내림차순 정렬 (비밀번호 제외)
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    // 4단계 : Update - 일정 수정
    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        if (verifyPassword(id, requestDto.getPassword())) {
            Schedule schedule = findSchedule(id);
            schedule.update(requestDto);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return id;
    }

    // 5단계 : Delete - 일정 삭제
    public Long deleteSchedule(Long id, ScheduleRequestDto requestDto) {
        if (verifyPassword(id, requestDto.getPassword())) {
            Schedule schedule = findSchedule(id);
            scheduleRepository.delete(schedule);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return id;

    }

    //
    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당하는 일정이 없습니다."));
    }

    private boolean verifyPassword(Long id, String password) {
        return new ScheduleResponseDto(findSchedule(id)).getPassword().equals(password);
    }
}
