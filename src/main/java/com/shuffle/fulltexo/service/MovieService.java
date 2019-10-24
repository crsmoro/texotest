package com.shuffle.fulltexo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shuffle.fulltexo.repository.MovieRepository;
import com.shuffle.fulltexo.service.vo.MaxMinWinIntervalForProducers;
import com.shuffle.fulltexo.service.vo.StudiosWithWinCount;
import com.shuffle.fulltexo.service.vo.YearsWithMultipleWinners;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public MaxMinWinIntervalForProducers getMaxMinWinIntervalForProducers() {
		return new MaxMinWinIntervalForProducers(movieRepository.findMinWinIntervalForProducers(PageRequest.of(0, 1)), movieRepository.findMaxWinIntervalForProducers(PageRequest.of(0, 1)));
	}

	public YearsWithMultipleWinners getYearsWithMultipleWinners() {
		return new YearsWithMultipleWinners(movieRepository.findYearsWithMultipleWinners());
	}

	public StudiosWithWinCount getStudiosWithWinCount() {
		return new StudiosWithWinCount(movieRepository.findStudiosWithWinCount(PageRequest.of(0, 3)));
	}
}
