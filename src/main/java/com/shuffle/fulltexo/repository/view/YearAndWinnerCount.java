package com.shuffle.fulltexo.repository.view;

public class YearAndWinnerCount {

	private Long year;

	private Long winnerCount;

	public YearAndWinnerCount(Long year, Long winnerCount) {
		super();
		this.year = year;
		this.winnerCount = winnerCount;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getWinnerCount() {
		return winnerCount;
	}

	public void setWinnerCount(Long winnerCount) {
		this.winnerCount = winnerCount;
	}

	@Override
	public String toString() {
		return "YearAndWinner [year=" + year + ", winnerCount=" + winnerCount + "]";
	}
}
