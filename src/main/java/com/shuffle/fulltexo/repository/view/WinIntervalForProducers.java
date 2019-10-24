package com.shuffle.fulltexo.repository.view;

public class WinIntervalForProducers {

	private String producer;

	private Long previousWin;

	private Long followingWin;

	private Long interval;

	public WinIntervalForProducers(String producer, Long previousWin, Long followingWin, Long interval) {
		super();
		this.producer = producer;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		this.interval = interval;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Long getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(Long previousWin) {
		this.previousWin = previousWin;
	}

	public Long getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(Long followingWin) {
		this.followingWin = followingWin;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "WinIntervalForProducers [producer=" + producer + ", previousWin=" + previousWin + ", followingWin=" + followingWin + ", interval=" + interval + "]";
	}

}