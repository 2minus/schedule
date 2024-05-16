package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 3단계 일정 전체조회 : 작성일 기준 내림차순 정렬 (비밀번호 제외)
    List<Schedule> findAllByOrderByCreatedAtDesc();
}
