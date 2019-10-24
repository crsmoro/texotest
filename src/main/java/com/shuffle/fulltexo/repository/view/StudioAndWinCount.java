package com.shuffle.fulltexo.repository.view;

public class StudioAndWinCount {

	private String name;

	private Long winCount;

	public StudioAndWinCount(String name, Long winCount) {
		super();
		this.name = name;
		this.winCount = winCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getWinCount() {
		return winCount;
	}

	public void setWinCount(Long winCount) {
		this.winCount = winCount;
	}

	@Override
	public String toString() {
		return "StudioAndWinCount [name=" + name + ", winCount=" + winCount + "]";
	}
}
