package org.tomcurran.cs310.mu;

import aima.core.search.framework.HeuristicFunction;

public class TCHeuristicFunction implements HeuristicFunction {

	private MUGoalTest goalTest;

	public TCHeuristicFunction(MUGoalTest goalTest) {
		this.goalTest = goalTest;
	}

	@Override
	public double h(Object state) {
		MUState path = (MUState) state;
		return getTCHeurisitic(path);
	}

	private double getTCHeurisitic(MUState state) {
		if (goalTest.isGoalState(state)) {
			return 0;
		}
		String goal = goalTest.getGoalState().getTheorem();
		String current = state.getTheorem();
		double h1 = (charCount(current, 'I') - charCount(goal, 'I')) / 3.0;
		double h2 = (charCount(current, 'U') - charCount(goal, 'U')) / 2.0;
		double h3 = Math.sqrt(current.length() - goal.length());
		return (int) (h1 + h2 + h3);
	}

	private static int charCount(String haystack, char needle) {
		final char[] chars = haystack.toCharArray();
		int count = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (chars[i] == needle) {
				count++;
			}
		}
		return count;
	}

}