package org.tomcurran.cs310.mu;

import aima.core.search.framework.GoalTest;

public class MUGoalTest implements GoalTest {

	private MUState goalState;

	public MUGoalTest(MUState goalState) {
		this.goalState = goalState;
	}

	@Override
	public boolean isGoalState(Object state) {
		return ((MUState) state).equals(goalState);
	}

	public MUState getGoalState() {
		return goalState;
	}

}