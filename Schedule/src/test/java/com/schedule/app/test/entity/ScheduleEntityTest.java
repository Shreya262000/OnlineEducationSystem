package com.schedule.app.test.entity;

import org.junit.jupiter.api.Test;

import com.schedule.entity.ScheduleEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScheduleEntityTest {

    @Test
    public void testScheduleEntityCreation() {
        LocalDate startDate = LocalDate.of(2023, 8, 11);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        ScheduleEntity schedule = new ScheduleEntity(1, "Math Class", "2 hours", "John Doe", startDate, endDate);

        assertEquals(1, schedule.getId());
        assertEquals("Math Class", schedule.getTitle());
        assertEquals("2 hours", schedule.getDuration());
        assertEquals("John Doe", schedule.getInstructorname());
        assertEquals(startDate, schedule.getStartDate());
        assertEquals(endDate, schedule.getEndDate());
    }

    @Test
    public void testScheduleEntityToString() {
        LocalDate startDate = LocalDate.of(2023, 8, 11);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        ScheduleEntity schedule = new ScheduleEntity(1, "Math Class", "2 hours", "John Doe", startDate, endDate);

        String expectedToString = "ScheduleEntity(id=1, title=Math Class, duration=2 hours, instructorname=John Doe, startDate=2023-08-11, endDate=2023-12-31)";
        assertEquals(expectedToString, schedule.toString());
    }

    @Test
    public void testScheduleEntityGettersAndSetters() {
        ScheduleEntity schedule = new ScheduleEntity();

        schedule.setId(1);
        assertEquals(1, schedule.getId());

        schedule.setTitle("Math Class");
        assertEquals("Math Class", schedule.getTitle());

        schedule.setDuration("2 hours");
        assertEquals("2 hours", schedule.getDuration());

        schedule.setInstructorname("John Doe");
        assertEquals("John Doe", schedule.getInstructorname());

        LocalDate startDate = LocalDate.of(2023, 8, 11);
        schedule.setStartDate(startDate);
        assertEquals(startDate, schedule.getStartDate());

        LocalDate endDate = LocalDate.of(2023, 12, 31);
        schedule.setEndDate(endDate);
        assertEquals(endDate, schedule.getEndDate());
    }

    @Test
    public void testScheduleEntityConstruction() {
        LocalDate startDate = LocalDate.of(2023, 8, 11);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        ScheduleEntity schedule = new ScheduleEntity(1, "Math Class", "2 hours", "John Doe", startDate, endDate);

        assertNotNull(schedule);
        assertEquals(1, schedule.getId());
        assertEquals("Math Class", schedule.getTitle());
        assertEquals("2 hours", schedule.getDuration());
        assertEquals("John Doe", schedule.getInstructorname());
        assertEquals(startDate, schedule.getStartDate());
        assertEquals(endDate, schedule.getEndDate());
    }
}
