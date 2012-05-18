package org.tomcurran.cs310.mu;

public class MUState {

	private String theorem;

	public MUState(MUState state) {
		theorem = state.getTheorem();
	}

	public MUState(String axiom) {
		theorem = axiom.toUpperCase();
	}

	public String getTheorem() {
		return theorem;
	}

	public boolean isRule1() {
		return theorem.endsWith("I");
	}

	public boolean isRule2() {
		return theorem.startsWith("M");
	}

	public boolean isRule3(int location) {
		return theorem.substring(location, location + 3).equals("III");
	}

	public boolean isRule4(int location) {
		return theorem.subSequence(location, location + 2).equals("UU");
	}

	public void useRule1() {
		theorem += "U";
	}

	public void useRule2() {
		theorem += theorem.substring(1);
	}

	public void useRule3(int location) {
		theorem = theorem.substring(0, location) + "U"
				+ theorem.substring(location + 3);
	}

	public void useRule4(int location) {
		theorem = theorem.substring(0, location)
				+ theorem.substring(location + 2);
	}

	@Override
	public String toString() {
		return getTheorem().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		MUState aState = (MUState) o;
		return this.getTheorem().equals(aState.getTheorem());
	}

	@Override
	public int hashCode() {
		return getTheorem().hashCode();
	}

}