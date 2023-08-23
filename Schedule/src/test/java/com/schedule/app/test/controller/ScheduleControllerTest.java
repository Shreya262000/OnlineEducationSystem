package com.schedule.app.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.schedule.controller.ScheduleController;
import com.schedule.entity.ScheduleEntity;
import com.schedule.exceptions.ScheduleNotFoundException;
import com.schedule.service.ScheduleService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ScheduleControllerTest {

	@Mock
	private ScheduleService scheduleService;

	@InjectMocks
	private ScheduleController scheduleController;

	private ScheduleEntity scheduleEntity1;
	private ScheduleEntity scheduleEntity2;

	@BeforeEach
	public void setUp() {
		// Initialize test data
		scheduleEntity1 = new ScheduleEntity();
		scheduleEntity2 = new ScheduleEntity();
	}

	@Test
	public void testAddSchedule() {
		// Mock the service method
		doNothing().when(scheduleService).insert(any(ScheduleEntity.class));

		// Call the controller method
		ResponseEntity<String> responseEntity = scheduleController.addSchedule(scheduleEntity1);

		// Verify the response
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Schedule Posted", responseEntity.getBody());

		// Verify that the service method was called once with the correct argument
		verify(scheduleService, times(1)).insert(scheduleEntity1);
	}

	@Test
	public void testGetAllSchedules() throws ScheduleNotFoundException {
		// Mock the service method
		doReturn(Arrays.asList(scheduleEntity1, scheduleEntity2)).when(scheduleService).findAllSchedules();

		// Call the controller method
		List<ScheduleEntity> schedules = scheduleController.getAllSchedules();

		// Verify the response
		assertEquals(2, schedules.size());
		assertEquals(scheduleEntity1, schedules.get(0));
		assertEquals(scheduleEntity2, schedules.get(1));

		// Verify that the service method was called once
		verify(scheduleService, times(1)).findAllSchedules();
	}

	@Test
	public void testDeleteScheduleById() throws ScheduleNotFoundException {
		int scheduleId = 1;

		// Mock the service method
		doReturn(Optional.of(scheduleEntity1)).when(scheduleService).deletescheduleById(scheduleId);

		// Call the controller method
		ResponseEntity<Object> responseEntity = scheduleController.deletescheduleById(scheduleId);

		// Verify the response
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(scheduleEntity1, responseEntity.getBody());

		// Verify that the service method was called once with the correct argument
		verify(scheduleService, times(1)).deletescheduleById(scheduleId);
	}

	@Test
	public void testUpdateSchedule() throws ScheduleNotFoundException {
	    int scheduleId = 1;

	    // Mock the service methods
	    when(scheduleService.getScheduleById(scheduleId)).thenReturn(Optional.of(scheduleEntity1));
	    doNothing().when(scheduleService).postScheduleEntity(any(ScheduleEntity.class));

	    // Call the controller method
	    ResponseEntity<String> responseEntity = scheduleController.updateSchedule(scheduleId, scheduleEntity2);

	    // Verify the response
	    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	    assertEquals("Schedule Updated", responseEntity.getBody());

	    // Verify that the service methods were called once with the correct arguments
	    verify(scheduleService, times(1)).getScheduleById(scheduleId);
	    verify(scheduleService, times(1)).postScheduleEntity(scheduleEntity1);
	}


}
