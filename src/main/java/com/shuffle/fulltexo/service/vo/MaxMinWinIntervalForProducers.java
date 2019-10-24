package com.shuffle.fulltexo.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.shuffle.fulltexo.repository.view.WinIntervalForProducers;

public class MaxMinWinIntervalForProducers {

	private List<WinIntervalForProducers> min = new ArrayList<WinIntervalForProducers>();

	private List<WinIntervalForProducers> max = new ArrayList<WinIntervalForProducers>();

	public MaxMinWinIntervalForProducers(List<WinIntervalForProducers> min, List<WinIntervalForProducers> max) {
		super();
		this.min = min;
		this.max = max;
	}

	public List<WinIntervalForProducers> getMin() {
		return min;
	}

	public void setMin(List<WinIntervalForProducers> min) {
		this.min = min;
	}

	public List<WinIntervalForProducers> getMax() {
		return max;
	}

	public void setMax(List<WinIntervalForProducers> max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "MaxMinWinIntervalForProducers [min=" + min + ", max=" + max + "]";
	}
}
