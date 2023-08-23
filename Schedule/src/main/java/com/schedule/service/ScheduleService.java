package com.schedule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schedule.entity.ScheduleEntity;
import com.schedule.exceptions.ScheduleNotFoundException;
import com.schedule.repository.ScheduleRepository;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	public ScheduleService(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}

	public List<ScheduleEntity> findAllShedules() throws ScheduleNotFoundException {
		return scheduleRepository.findAll();
	}

	public void insert(ScheduleEntity scheduleEntity) {
		scheduleRepository.save(scheduleEntity);

	}

	public void postScheduleEntity(ScheduleEntity scheduleDB) throws ScheduleNotFoundException {
		scheduleRepository.save(scheduleDB);

	}

	public Optional<ScheduleEntity> getSchedulesById(int sid) {
		Optional<ScheduleEntity> optional = scheduleRepository.findById(sid);
		return optional;
	}

	public List<ScheduleEntity> findAllSchedules() throws ScheduleNotFoundException {

		return scheduleRepository.findAll();
	}

	public Optional<ScheduleEntity> getScheduleById(int id) {
		Optional<ScheduleEntity> optional = scheduleRepository.findById(id);
		return optional;

	}

	public Optional<Object> deletescheduleById(int scheduleId) throws ScheduleNotFoundException {

		Optional<Object> optionalSchedule = Optional.empty();

		return optionalSchedule;

	}
}
