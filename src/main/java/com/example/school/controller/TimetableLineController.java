package com.example.school.controller;

import com.example.school.dto.timetable.NewOrUpdatedTimetableLineDto;
import com.example.school.dto.timetable.TimetableLineDto;
import com.example.school.dto.timetable.TimetableLinePage;
import com.example.school.service.TimetableLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timetablelines")
public class TimetableLineController {

    private final TimetableLineService timetableLineService;

    @GetMapping
    public ResponseEntity<TimetableLinePage> findAll(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(timetableLineService.findAll(page));
    }

    @PostMapping
    public ResponseEntity<TimetableLineDto> save(
            @RequestBody NewOrUpdatedTimetableLineDto newTimetableLineDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(timetableLineService.save(newTimetableLineDto));
    }
}
