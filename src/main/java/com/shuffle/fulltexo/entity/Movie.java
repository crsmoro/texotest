package com.shuffle.fulltexo.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.shuffle.fulltexo.importer.adapters.BooleanValue;
import com.shuffle.fulltexo.importer.annotations.ImportField;
import com.shuffle.fulltexo.importer.annotations.ImportFieldCustomValue;

@Entity
public class Movie extends GenericEntity {

	private Long year;

	private String title;

	@ManyToMany
	@ImportField(list = true, separatorList = ",|and")
	private Set<Studio> studios = new HashSet<Studio>();

	@ManyToMany
	@ImportField(list = true, separatorList = ",|and")
	private Set<Producer> producers = new HashSet<Producer>();

	@ImportFieldCustomValue(customValue = BooleanValue.class)
	private Boolean winner;

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Studio> getStudios() {
		return studios;
	}

	public void setStudios(Set<Studio> studios) {
		this.studios = studios;
	}

	public Set<Producer> getProducers() {
		return producers;
	}

	public void setProducers(Set<Producer> producers) {
		this.producers = producers;
	}

	public Boolean isWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "Movie [year=" + year + ", title=" + title + ", studios=" + Arrays.toString(studios.toArray()) + ", producers=" + Arrays.toString(producers.toArray()) + ", winner=" + winner + ", id=" + id + "]";
	}

}
