package com.schedule.app.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.schedule.entity.ScheduleEntity;
import com.schedule.repository.ScheduleRepository;

@DataJpaTest
@ActiveProfiles("test")
public class ScheduleRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Test
	public void testFindAllSchedules() {
		ScheduleEntity schedule1 = new ScheduleEntity();
		ScheduleEntity schedule2 = new ScheduleEntity();

		entityManager.persist(schedule1);
		entityManager.persist(schedule2);

		List<ScheduleEntity> schedules = scheduleRepository.findAll();

		assertEquals(2, schedules.size());
		// Perform more assertions on the list if needed
	}

	@Test
	public void testFindScheduleById() {
		ScheduleEntity schedule = new ScheduleEntity();

		entityManager.persist(schedule);

		Optional<ScheduleEntity> foundSchedule = scheduleRepository.findById(schedule.getId());

		assertEquals(true, foundSchedule.isPresent());
		assertEquals(schedule, foundSchedule.get());
	}

	@Test
	public void testSaveSchedule() {
		ScheduleEntity schedule = new ScheduleEntity();

		ScheduleEntity savedSchedule = scheduleRepository.save(schedule);

		assertEquals(schedule, savedSchedule);
	}

	@Test
	public void testDeleteSchedule() {
		ScheduleEntity schedule = new ScheduleEntity();

		entityManager.persist(schedule);

		scheduleRepository.deleteById(schedule.getId());

		assertEquals(0, scheduleRepository.count());
	}
	
}
