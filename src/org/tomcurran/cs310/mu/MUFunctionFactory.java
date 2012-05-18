package org.tomcurran.cs310.mu;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

public class MUFunctionFactory {

	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new MIUActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new MIUResultFunction();
		}
		return _resultFunction;
	}

	private static class MIUActionsFunction implements ActionsFunction {

		public Set<Action> actions(Object state) {
			MUState ms = (MUState) state;
			int length = ms.getTheorem().length();

			Set<Action> actions = new LinkedHashSet<Action>();

			if (ms.isRule1()) {
				actions.add(new MUAction(MUAction.RULE1));
			}
			if (ms.isRule2()) {
				actions.add(new MUAction(MUAction.RULE2));
			}
			for (int i = 0; i < length - 2; i++) {
				if (ms.isRule3(i)) {
					actions.add(new MUAction(MUAction.RULE3, i));
				}
			}
			for (int i = 0; i < length - 1; i++) {
				if (ms.isRule4(i)) {
					actions.add(new MUAction(MUAction.RULE4, i));
				}
			}

			return actions;
		}
	}

	private static class MIUResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			if (a instanceof MUAction) {
				MUAction ma = (MUAction) a;
				MUState ns = new MUState((MUState) s);

				if (ma.getName().equals(MUAction.RULE1)) {
					ns.useRule1();
				} else if (ma.getName().equals(MUAction.RULE2)) {
					ns.useRule2();
				} else if (ma.getName().equals(MUAction.RULE3)) {
					ns.useRule3(ma.getLocation());
				} else if (ma.getName().equals(MUAction.RULE4)) {
					ns.useRule4(ma.getLocation());
				}

				s = ns;
			}
			// if action is not understood or is a NoOp the result will be the
			// current state.
			return s;
		}
	}

}