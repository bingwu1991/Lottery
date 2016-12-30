package com.demo.mvc.DTO;

import java.util.Map;

/**
 * @author wubing
 *
 */
public class StatisticResult {

	private Map<String, Map<String, Integer>> firstResult;
	private Map<String, Map<String, Integer>> secondResult;
	private Map<String, Map<String, Integer>> lastResult;
	private Map<String, Map<String, Integer>> fourResult;
	private Map<String, Map<String, Integer>> fiveResult;

	public StatisticResult(Map<String, Map<String, Integer>> firstResult, Map<String, Map<String, Integer>> secondResult,
			Map<String, Map<String, Integer>> lastResult, Map<String, Map<String, Integer>> fourResult,
			Map<String, Map<String, Integer>> fiveResult) {
		this.firstResult = firstResult;
		this.secondResult = secondResult;
		this.lastResult = lastResult;
		this.fourResult = fourResult;
		this.fiveResult = fiveResult;
	}

	public Map<String, Map<String, Integer>> getFirstResult() {
		return firstResult;
	}

	public Map<String, Map<String, Integer>> getFourResult() {
		return fourResult;
	}

	public void setFourResult(Map<String, Map<String, Integer>> fourResult) {
		this.fourResult = fourResult;
	}

	public Map<String, Map<String, Integer>> getFiveResult() {
		return fiveResult;
	}

	public void setFiveResult(Map<String, Map<String, Integer>> fiveResult) {
		this.fiveResult = fiveResult;
	}

	public void setFirstResult(Map<String, Map<String, Integer>> firstResult) {
		this.firstResult = firstResult;
	}

	public Map<String, Map<String, Integer>> getSecondResult() {
		return secondResult;
	}

	public void setSecondResult(Map<String, Map<String, Integer>> secondResult) {
		this.secondResult = secondResult;
	}

	public Map<String, Map<String, Integer>> getLastResult() {
		return lastResult;
	}

	public void setLastResult(Map<String, Map<String, Integer>> lastResult) {
		this.lastResult = lastResult;
	}
}
