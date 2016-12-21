package mixprob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NoFeasibleSolutionException;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class AprioriAlgorithm {
	public static Result executeAprioriAlgorithm(InputData inputData, HashMap<Vitamin, Double> dailyDemandForVitamins) {
		ArrayList<ObjectiveFunction> objectiveFunctions = inputData.getObjectiveFunctions();

		Collection<LinearConstraint> constraints = createConstraints(dailyDemandForVitamins);

		PointValuePair result = method(objectiveFunctions, constraints);

		Result results = new Result();
		
		try {
			double[] result2 = result.getPoint();
			results.setAmountBread(result2[0]);
			results.setAmountMilk(result2[1]);
			results.setAmountChicken(result2[2]);
			results.setAmountChocolate(result2[3]);
			results.setAmountOrange(result2[4]);
			results.setAmountCarrot(result2[5]);
		} catch (NullPointerException e) {
			throw new NoFeasibleSolutionException();
		}
		
		return results;
	}

	private static PointValuePair method(ArrayList<ObjectiveFunction> objectiveFunctions,
			Collection<LinearConstraint> constraints) {
		double[] coefficients;
		LinearObjectiveFunction objFunc;
		PointValuePair solution;

		switch (objectiveFunctions.get(0).getValue()) {
		case 1:
			coefficients = new double[] { 0.6, 0.5, 1.2, 3.5, 0.5, 0.3 };
			objFunc = new LinearObjectiveFunction(coefficients, 0);
			solution = executeSimplexSolverAlgorithm(constraints, objFunc);
			return method2(objectiveFunctions.get(1), objectiveFunctions.get(2), coefficients, constraints, solution);
		case 2:
			coefficients = new double[] { Mischievousness.LOW.value, Mischievousness.LOW.value,
					Mischievousness.AVERAGE.value, Mischievousness.HIGH.value, Mischievousness.LOW.value,
					Mischievousness.LOW.value };
			objFunc = new LinearObjectiveFunction(coefficients, 0);
			solution = executeSimplexSolverAlgorithm(constraints, objFunc);
			return method2(objectiveFunctions.get(1), objectiveFunctions.get(2), coefficients, constraints, solution);
		case 3:
			coefficients = new double[] { 0.8071, 0.844, 2.9557, 3.438, 53.5081, 11.7604 };
			objFunc = new LinearObjectiveFunction(coefficients, 0);
			solution = executeSimplexSolverAlgorithm(constraints, objFunc);
			return method2(objectiveFunctions.get(1), objectiveFunctions.get(2), coefficients, constraints, solution);
		default:
			throw new IllegalArgumentException("Podana funkcja celu nie istnieje!");
		}
	}

	private static PointValuePair method2(ObjectiveFunction objectiveFunctionSecond,
			ObjectiveFunction objectiveFunctionThird, double[] coefficients, Collection<LinearConstraint> constraints,
			PointValuePair solution) {
		PointValuePair solutionSecond = null;

		for (double epsilon = 1; epsilon <= 2; epsilon += 0.01) {
			System.out.println("Pierwszy warunek ogr.: " + epsilon);
			constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));

			double[] coefficientsSecond;
			LinearObjectiveFunction objFunc;

			switch (objectiveFunctionSecond.getValue()) {
			case 1:
				coefficientsSecond = new double[] { 0.6, 0.5, 1.2, 3.5, 0.5, 0.3 };
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionSecond = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return method3(objectiveFunctionThird, coefficientsSecond, constraints, solutionSecond);
			case 2:
				coefficientsSecond = new double[] { Mischievousness.LOW.value, Mischievousness.LOW.value,
						Mischievousness.AVERAGE.value, Mischievousness.HIGH.value, Mischievousness.LOW.value,
						Mischievousness.LOW.value };
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionSecond = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return method3(objectiveFunctionThird, coefficientsSecond, constraints, solutionSecond);
			case 3:
				coefficientsSecond = new double[] { 0.8071, 0.844, 2.9557, 3.438, 53.5081, 11.7604 };
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionSecond = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return method3(objectiveFunctionThird, coefficientsSecond, constraints, solutionSecond);
			default:
				throw new IllegalArgumentException("Podana funkcja celu nie istnieje!");
			}

		}
		System.out.println("Nie ma wyniku 2.!");
		return solutionSecond;
	}

	private static PointValuePair method3(ObjectiveFunction objectiveFunctionThird, double[] coefficients,
			Collection<LinearConstraint> constraints, PointValuePair solution) {
		PointValuePair solutionThird = null;

		for (double epsilon = 1; epsilon <= 2; epsilon += 0.01) {
			System.out.println("Drugi warunek ogr.: " + epsilon);
			constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
			LinearObjectiveFunction objFunc;
			double[] coefficientsSecond;
			switch (objectiveFunctionThird.getValue()) {
			case 1:
				coefficientsSecond = new double[] { 0.6, 0.5, 1.2, 3.5, 0.5, 0.3 };
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionThird = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return solutionThird;
			case 2:
				coefficientsSecond = new double[] { Mischievousness.LOW.value, Mischievousness.LOW.value,
						Mischievousness.AVERAGE.value, Mischievousness.HIGH.value, Mischievousness.LOW.value,
						Mischievousness.LOW.value };
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionThird = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return solutionThird;
			case 3:
				coefficientsSecond = new double[] { 0.8071, 0.844, 2.9557, 3.438, 53.5081, 11.7604 };
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionThird = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return solutionThird;
			default:
				throw new IllegalArgumentException("Podana funkcja celu nie istnieje!");
			}

		}
		System.out.println("Nie ma wyniku 3.!");
		return solutionThird;
	}

	/**
	 * Method for creating linear constraints.
	 * 
	 * @param dailyDemandForVitamins
	 *            - the right part of the equations (minimum amount of daily
	 *            demand for each vitamin)
	 * @return linear constraints
	 */
	private static Collection<LinearConstraint> createConstraints(HashMap<Vitamin, Double> dailyDemandForVitamins) {
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

		constraints.add(new LinearConstraint(new double[] { 0.0021, 0.576, 0.0157, 0.053, 0.0681, 5.0624 },
				Relationship.GEQ, dailyDemandForVitamins.get(Vitamin.A)));
		constraints.add(new LinearConstraint(new double[] { 0.075, 0.038, 0.43, 0.045, 0.06, 0.138 }, Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.B6)));
		constraints.add(new LinearConstraint(new double[] { 0.4, 0.2, 2.3, 0.2, 53.2, 5.9 }, Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.C)));
		constraints.add(new LinearConstraint(new double[] { 0.33, 0.03, 0.21, 3.14, 0.18, 0.66 }, Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.E)));
		constraints.add(new LinearConstraint(new double[] { 1, 1, 1, 1, 1, 1 }, Relationship.GEQ, 0));

		return constraints;
	}

	/**
	 * Method for executing SimplexSolver algorithm
	 * 
	 * @param constraints
	 *            - linear constraint for SimplexSolver algorithm
	 * @param objectiveFunction
	 *            - linear objective function for SimplexSolver algorithm
	 * @return solutions of objective function with given constraints
	 */
	private static PointValuePair executeSimplexSolverAlgorithm(Collection<LinearConstraint> constraints,
			LinearObjectiveFunction objectiveFunction) {
		SimplexSolver solver = new SimplexSolver();

		PointValuePair solution = solver.optimize(new MaxIter(100), objectiveFunction,
				new LinearConstraintSet(constraints), GoalType.MINIMIZE, new NonNegativeConstraint(true));

		return solution;
	}
}
