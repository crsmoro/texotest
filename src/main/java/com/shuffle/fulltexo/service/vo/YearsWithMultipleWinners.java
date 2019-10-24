package com.shuffle.fulltexo.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.shuffle.fulltexo.repository.view.YearAndWinnerCount;

public class YearsWithMultipleWinners {

	private List<YearAndWinnerCount> years = new ArrayList<YearAndWinnerCount>();

	public YearsWithMultipleWinners(List<YearAndWinnerCount> years) {
		super();
		this.years = years;
	}

	public List<YearAndWinnerCount> getYears() {
		return years;
	}

	public void setYears(List<YearAndWinnerCount> years) {
		this.years = years;
	}

	@Override
	public String toString() {
		return "YearWithMultipleWinners [years=" + years + "]";
	}
}
