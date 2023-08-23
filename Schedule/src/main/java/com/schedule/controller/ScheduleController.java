
package com.schedule.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.entity.ScheduleEntity;
import com.schedule.exceptions.ScheduleNotFoundException;
import com.schedule.logger.LoggerUtil;
import com.schedule.service.ScheduleService;

@RestController
@RequestMapping("/apischedule")
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/schedule/save")
	public ResponseEntity<String> addSchedule(@RequestBody ScheduleEntity scheduleEntity) {
		scheduleService.insert(scheduleEntity);
		LoggerUtil.logInfo("schedule details are posted");
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Schedule Posted", HttpStatus.CREATED);
		return responseEntity;
	}

	@DeleteMapping("/schedule/delete/{id}")

	public ResponseEntity<Object> deletescheduleById(@PathVariable("id") int scheduleId)

			throws ScheduleNotFoundException {

		Optional<Object> optionalSchedule = scheduleService.deletescheduleById(scheduleId);

		if (!optionalSchedule.isPresent()) {

			LoggerUtil.logInfo("schedule details are deleted");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Schedule with schedule id:" + scheduleId + " not found!!!");

		}

		Object schedule = optionalSchedule.get();

		return ResponseEntity.status(HttpStatus.OK).body(schedule);
	}

	@PutMapping("/schedule/update/{sid}")
	public ResponseEntity<String> updateSchedule(@PathVariable("sid") int sid, @RequestBody ScheduleEntity scheduleNew)
			throws ScheduleNotFoundException {
		Optional<ScheduleEntity> optional = scheduleService.getScheduleById(sid);
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID");
		}
		ScheduleEntity scheduleDB = optional.get();
		if (scheduleNew.getDuration() != null)
			scheduleDB.setDuration(scheduleNew.getDuration());
		if (scheduleNew.getInstructorname() != null)
			scheduleDB.setInstructorname(scheduleNew.getInstructorname());

		scheduleService.postScheduleEntity(scheduleDB);
		LoggerUtil.logInfo("schedule details are updated");
		return ResponseEntity.status(HttpStatus.OK).body("Schedule Updated");

	}

	@GetMapping("/schedule/all")

	public List<ScheduleEntity> getAllSchedules() throws ScheduleNotFoundException {

		List<ScheduleEntity> schedule = scheduleService.findAllSchedules();
		LoggerUtil.logInfo(" all schedule details are displayed");

		return schedule;

	}

	@GetMapping("/one/{id}")

	public ResponseEntity<Object> getScheduleById(@PathVariable("id") int id) throws ScheduleNotFoundException {

		Optional<ScheduleEntity> optional = scheduleService.getScheduleById(id);

		if (!optional.isPresent()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID");

		}
		ScheduleEntity schedule = optional.get();
		LoggerUtil.logInfo("schedule details are posted");
		return ResponseEntity.status(HttpStatus.OK).body(schedule);

	}

}