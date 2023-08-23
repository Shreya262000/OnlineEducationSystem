package com.schedule.app.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.schedule.entity.ScheduleEntity;
import com.schedule.exceptions.ScheduleNotFoundException;
import com.schedule.repository.ScheduleRepository;
import com.schedule.service.ScheduleService;

@SpringBootTest
public class ScheduleServiceTest {

	@MockBean
	private ScheduleRepository scheduleRepository;

	private ScheduleService scheduleService;

	@BeforeEach
	public void setUp() {
		scheduleService = new ScheduleService(scheduleRepository);
	}

	@Test
	public void testGetScheduleById() {
		// Arrange
		int scheduleId = 1;
		ScheduleEntity scheduleEntity = new ScheduleEntity();

		// Mock the behavior of scheduleRepository.findById()
		when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(scheduleEntity));

		// Act
		Optional<ScheduleEntity> result = scheduleService.getScheduleById(scheduleId);

		// Assert
		assertTrue(result.isPresent());
		assertEquals(scheduleEntity, result.get());

		// Verify that scheduleRepository.findById() was called with the correct
		// parameter
		verify(scheduleRepository).findById(scheduleId);
	}

	@Test
	public void testGetScheduleByIdNotFound() {
		// Arrange
		int scheduleId = 1;

		// Mock the behavior of scheduleRepository.findById() to return an empty
		// Optional
		when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

		// Act
		Optional<ScheduleEntity> result = scheduleService.getScheduleById(scheduleId);

		// Assert
		assertFalse(result.isPresent());

		// Verify that scheduleRepository.findById() was called with the correct
		// parameter
		verify(scheduleRepository).findById(scheduleId);
	}

	@Test
	public void testFindAllSchedules() throws ScheduleNotFoundException {
		// Arrange
		List<ScheduleEntity> schedules = new ArrayList<>();
		schedules.add(new ScheduleEntity());
		schedules.add(new ScheduleEntity());

		// Mock the behavior of scheduleRepository.findAll()
		when(scheduleRepository.findAll()).thenReturn(schedules);

		// Act
		List<ScheduleEntity> result = scheduleService.findAllSchedules();

		// Assert
		assertEquals(schedules, result);

		// Verify that scheduleRepository.findAll() was called
		verify(scheduleRepository).findAll();
	}

	@Test
	public void testInsertScheduleEntity() {
		// Arrange
		ScheduleEntity scheduleEntity = new ScheduleEntity();

		// Act
		scheduleService.insert(scheduleEntity);

		// Verify that scheduleRepository.save() was called with the correct parameter
		verify(scheduleRepository).save(scheduleEntity);
	}
}
