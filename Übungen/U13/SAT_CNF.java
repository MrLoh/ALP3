package ueb13;

import java.util.*;

public class SAT_CNF {
	/***
	 * Notes: 
	 * -All literals must be introduced in ascending order, and as described
	 *  in exercise 3. 
	 * -It is for max. 9 literals. It should be enough to test if it works.
	 ***/

	LinkedList<LinkedList<Integer>> clauses = new LinkedList<LinkedList<Integer>>();

	/* Constructor parses input string and initializes the clauses */
	public SAT_CNF(String s) {
		clauses.add(new LinkedList<Integer>());// Initialize clause
		int currentClause = 0;
		for (int i = 0; i < s.length(); i++) {
			char currentChar = s.charAt(i);
			// If currentChar is a number, add to current clause
			if (isNumber(currentChar)) {
				int number = Character.getNumericValue(currentChar);
				// check if literal is negative
				if (i != 0) {
					if (s.charAt(i - 1) == '-')
						number *= (-1);
				}
				clauses.get(currentClause).add(number);
			}
			// If comma, add one more clause
			else if (currentChar == ',') {
				currentClause++;
				clauses.add(new LinkedList<Integer>());
			}
		}
	}

	public boolean isNumber(char c) {
		return ((int) c > 47 && (int) c < 58);
	}

	public int minLiteral(LinkedList<LinkedList<Integer>> clauses) {
		int min = clauses.peek().peek();
		for (LinkedList<Integer> clause : clauses)
			min = Math.abs(clause.peek()) < Math.abs(min) ? clause.peek() : min;
		return Math.abs(min);
	}

	public boolean allClausesLengthOneAndLiteralsEqual(
			LinkedList<LinkedList<Integer>> clauses) {
		boolean allLengthOne = true;
		boolean allEqual = true;
		int first = Math.abs(clauses.peek().peek());
		for (LinkedList<Integer> clause : clauses) {
			allLengthOne &= (clause.size() == 1);
			allEqual &= (first == Math.abs(clause.peek()));
		}
		return allLengthOne & allEqual;
	}

	public boolean cnf_sat(LinkedList<LinkedList<Integer>> clauses) {
		/* First: Clone clauses */
		LinkedList<LinkedList<Integer>> cloneClauses = new LinkedList<LinkedList<Integer>>();
		for (LinkedList<Integer> clause : clauses)
			cloneClauses.add((LinkedList<Integer>) clause.clone());
		return cnf_satZero(cloneClauses) || cnf_satOne(clauses);
	}

	/***
	 * SecondAnker:When there is only 1 literal pro clause, and all literals are
	 * equal, check: 
	 * if all negative -> SAT when last literal = 0 (could also be 1) 
	 * if all positive -> SAT when last literal = 1 (could also be 0) 
	 * else ->no SAT
	 ***/
	public boolean secondAnker(int oneOrZero,
			LinkedList<LinkedList<Integer>> clauses) {
		boolean all_positive = clauses.peek().peek() > 0;
		boolean all_negative = clauses.peek().peek() < 0;
		int zeroOrOne = oneOrZero == 1 ? 1 : 0;
		for (int i = 1; i < clauses.size(); i++) {
			all_positive &= clauses.get(i).peek() > 0;
			all_negative &= clauses.get(i).peek() > 0;
		}
		if (all_positive)
			System.out
					.println("Last literal is 1. It can also be 0 if there is not a clause that only contains it");
		else if (all_negative)
			System.out
					.println("Last literal is 0. It can also be 1 if there is not a clause that only contains it");
		else
			System.out.println("When replacing with " + zeroOrOne
					+ ", CNF is not SAT\n");

		return all_positive || all_negative;
	}

	// replace literals with value 1.
	public boolean cnf_satOne(LinkedList<LinkedList<Integer>> clauses) {
		if (clauses.isEmpty()) {
			System.out
					.println("CNF is SAT, rest of literals can have any value");
			return true;
		} else if (allClausesLengthOneAndLiteralsEqual(clauses)) {
			return secondAnker(1, clauses);
		} else {
			int minLit = minLiteral(clauses);
			int negMinLit = minLit * (-1);
			System.out.println("CNF_SAT_ONE Literal x" + minLit + ": 1");
			// Pos. literal->remove clause; Neg. literal->remove literal.
			for (int i = 0; i < clauses.size(); i++) {
				if (clauses.get(i).peek() == minLit)// positive literal
					clauses.remove(i--);
				else if (clauses.get(i).peek() == negMinLit) {
					clauses.get(i).removeFirst();     // neg. literal
					// if clause only hat one element, remove clause
					if (clauses.get(i).isEmpty())
						clauses.remove(i--);
				}
			}
			return cnf_satOne(clauses);
		}
	}

	// replace literals with value 0.
	public boolean cnf_satZero(LinkedList<LinkedList<Integer>> clauses) {
		if (clauses.isEmpty()) {
			System.out
					.println("CNF is SAT, rest of literals can have any value");
			return true;
		} else if (allClausesLengthOneAndLiteralsEqual(clauses)) {
			return secondAnker(0, clauses);
		} else {
			int minLit = minLiteral(clauses);
			int negMinLit = minLit * (-1);
			System.out.println("CNF_SAT_ZERO Literal x" + minLit + ": 0");
			// Pos. literal->remove literal; Neg. literal->remove clause.
			for (int i = 0; i < clauses.size(); i++) {
				if (clauses.get(i).peek() == minLit) {// positive literal
					clauses.get(i).removeFirst();
					if (clauses.get(i).isEmpty())
						clauses.remove(i--);
				} else if (clauses.get(i).peek() == negMinLit)// neg. literal
					clauses.remove(i--);
			}
			return cnf_satZero(clauses);
		}
	}

	public static void main(String[] args) {
		SAT_CNF alg = new SAT_CNF("1 2 3, -1, 2 -3");
		System.out.println(alg.clauses);
		alg.cnf_sat(alg.clauses);
	}

}