package org.tomcurran.cs310.mu;

import aima.core.agent.impl.DynamicAction;

public class MUAction extends DynamicAction {

	public static final String RULE1 = "RULE1"; // xI -> xIU
	public static final String RULE2 = "RULE2"; // Mx -> Mxx
	public static final String RULE3 = "RULE3"; // xIIIy -> xUy
	public static final String RULE4 = "RULE4"; // xUUy -> xy

	public static final String ATTRIBUTE_RULE_LOC = "location";

	public MUAction(String type) {
		this(type, -1);
	}

	public MUAction(String type, int location) {
		super(type);
		setAttribute(ATTRIBUTE_RULE_LOC, location);
	}

	public int getLocation() {
		return (Integer) getAttribute(ATTRIBUTE_RULE_LOC);
	}

}