/*******************************************************************************
 * Copyright (c) 2014 Johannes Lerch.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Johannes Lerch - initial API and implementation
 ******************************************************************************/
package heros.utilities;

import heros.solver.JoinHandlingNode;
import heros.solver.LinkedNode;
import heros.solver.JoinHandlingNode.JoinKey;

public class Fact implements JoinHandlingNode<Fact> {

	public final String name;
	
	public Fact(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Fact))
			return false;
		Fact other = (Fact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "[Fact "+name+"]";
	}

	@Override
	public void setCallingContext(Fact callingContext) {
		
	}

	@Override
	public heros.solver.JoinHandlingNode.JoinKey createJoinKey() {
		return new TestJoinKey();
	}

	@Override
	public boolean handleJoin(Fact joiningNode) {
		return true;
	}

	private class TestJoinKey extends JoinKey {

		private Fact getFact() {
			return Fact.this;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof TestJoinKey) {
				return getFact().equals(((TestJoinKey) obj).getFact());
			}
			throw new IllegalArgumentException();
		}

		@Override
		public int hashCode() {
			return Fact.this.hashCode();
		}
	}
}
