package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    public final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 1단계 : Create - 일정 작성
    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule() {
        return scheduleService.createSchedule();
    }

    // 2단계 : Read - 식별자로 선택한 일정 조회
    @GetMapping("/schedules")
    public ScheduleResponseDto getSchedules(@RequestParam Long id) {
        return scheduleService.getSchedule(id);
    }

    // 3단계 : ReadAll - 일정 전체조회 : 작성일 기준 내림차순 정렬 (비밀번호 제외)
    @GetMapping("/Schedules/All")
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // 4단계 : Update - 일정 수정
    @PutMapping("/schedules")
    public Long updateSchedule(@RequestParam Long id) {
        return scheduleService.updateSchedule(id);
    }

    // 5단계 : Delete - 일정 삭제
    @DeleteMapping("/schedules")
    public Long deleteSchedule(@RequestParam Long id) {
        return scheduleService.deleteSchedule(id);
    }

}
