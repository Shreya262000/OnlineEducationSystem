package com.schedule.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class ScheduleNotFoundException extends Exception {
	
	private String msg;




}
