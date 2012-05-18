package org.tomcurran.cs310.mu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

public class MUDemo {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("USEAGE: MUDemo [goal]");
			System.exit(0);
		}
		newMUDemo(args[0]);
	}

	private static MUState startState;
	private static MUGoalTest goalTest;
	private static Problem problem;
	private static HeuristicFunction tcHeuristicFunction;

	private static void newMUDemo(String goal) {
		startState = new MUState("MI");
		goalTest = new MUGoalTest(new MUState(goal));
		problem = new Problem(startState,
				MUFunctionFactory.getActionsFunction(),
				MUFunctionFactory.getResultFunction(), goalTest);
		tcHeuristicFunction = new TCHeuristicFunction(goalTest);

		doSearch("BFS", new BreadthFirstSearch(new TreeSearch()));
		// doSearch("DFS", new DepthFirstSearch(new GraphSearch()));
		doSearch("DLS (" + 7 + ")", new DepthLimitedSearch(7));
		doSearch("Iterative DS", new IterativeDeepeningSearch());
		doSearch("Greedy Best First Search (TCHeursitic)",
				new GreedyBestFirstSearch(new GraphSearch(),
						tcHeuristicFunction));
		doSearch("A(NOT*) Search (TCHeursitic)", new AStarSearch(
				new GraphSearch(), tcHeuristicFunction));
	}

	private static void doSearch(String type, Search search) {
		try {
			System.out.printf("MUDemo %s\n", type);
			SearchAgent agent = new SearchAgent(problem, search);
			printPath(startState, agent.getActions());
			printInstrumentation(agent.getInstrumentation());
			System.out.println();
		} catch (Exception e) {
			System.out.printf("%s: %s\n", type, e.getMessage());
		}
	}

	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.printf("%s: %s\n", key, property);
		}
	}

	private static void printPath(MUState state, List<Action> actions) {
		List<String> states = new ArrayList<String>();
		states.add(state.getTheorem());
		ResultFunction resultFunction = MUFunctionFactory.getResultFunction();
		for (int i = 0; i < actions.size(); i++) {
			state = (MUState) resultFunction.result(state, actions.get(i));
			states.add(state.getTheorem());
		}
		System.out.printf("Path: %s\n", states);
	}

}