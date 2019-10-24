package com.shuffle.fulltexo.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.shuffle.fulltexo.repository.view.StudioAndWinCount;

public class StudiosWithWinCount {

	private List<StudioAndWinCount> studios = new ArrayList<StudioAndWinCount>();

	public StudiosWithWinCount(List<StudioAndWinCount> studios) {
		super();
		this.studios = studios;
	}

	public List<StudioAndWinCount> getStudios() {
		return studios;
	}

	public void setStudios(List<StudioAndWinCount> studios) {
		this.studios = studios;
	}

	@Override
	public String toString() {
		return "StudiosWithWinCount [studios=" + studios + "]";
	}

}
