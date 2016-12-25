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
	public static Result executeAprioriAlgorithm(ArrayList<ObjectiveFunction> objectiveFunctions,
			HashMap<Vitamin, Double> dailyDemandForVitamins, HashMap<Vitamin, Double> maxDailyDemandForVitamins,
			HashMap<String, Product> products) {
		Collection<LinearConstraint> constraints = createConstraints(dailyDemandForVitamins, maxDailyDemandForVitamins,
				products);

		PointValuePair result = calculateFirstObjectiveFunction(objectiveFunctions, constraints, products);

		Result results;
		try {
			double[] resultPoints = result.getPoint();
			results = new Result(resultPoints);
		} catch (NullPointerException e) {
			throw new NoFeasibleSolutionException();
		}

		return results;
	}

	private static PointValuePair calculateFirstObjectiveFunction(ArrayList<ObjectiveFunction> objectiveFunctions,
			Collection<LinearConstraint> constraints, HashMap<String, Product> products) {
		double[] coefficients;
		LinearObjectiveFunction objFunc;
		PointValuePair solution;

		switch (objectiveFunctions.get(0).getValue()) {
		case 1:
			coefficients = Product.getPrices(products);
			objFunc = new LinearObjectiveFunction(coefficients, 0);
			solution = executeSimplexSolverAlgorithm(constraints, objFunc);
			return calculateSecondObjectiveFunction(objectiveFunctions.get(1), objectiveFunctions.get(2), coefficients,
					constraints, solution, products);
		case 2:
			coefficients = Product.getMischievousness(products);
			objFunc = new LinearObjectiveFunction(coefficients, 0);
			solution = executeSimplexSolverAlgorithm(constraints, objFunc);
			return calculateSecondObjectiveFunction(objectiveFunctions.get(1), objectiveFunctions.get(2), coefficients,
					constraints, solution, products);
		case 3:
			coefficients = Product.getSumOfVitamins(products);
			objFunc = new LinearObjectiveFunction(coefficients, 0);
			solution = executeSimplexSolverAlgorithm(constraints, objFunc);
			return calculateSecondObjectiveFunction(objectiveFunctions.get(1), objectiveFunctions.get(2), coefficients,
					constraints, solution, products);
		default:
			throw new IllegalArgumentException("Podana funkcja celu nie istnieje!");
		}
	}

	private static PointValuePair calculateSecondObjectiveFunction(ObjectiveFunction objectiveFunctionSecond,
			ObjectiveFunction objectiveFunctionThird, double[] coefficients, Collection<LinearConstraint> constraints,
			PointValuePair solution, HashMap<String, Product> products) {
		PointValuePair solutionSecond = null;

		for (double epsilon = 1; epsilon <= 2; epsilon += 0.01) {
			System.out.println("Pierwszy warunek ogr.: " + epsilon);
			constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));

			double[] coefficientsSecond;
			LinearObjectiveFunction objFunc;

			switch (objectiveFunctionSecond.getValue()) {
			case 1:
				coefficientsSecond = Product.getPrices(products);
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionSecond = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return calculateThirdObjectiveFunction(objectiveFunctionThird, coefficientsSecond, constraints,
						solutionSecond, products);
			case 2:
				coefficientsSecond = Product.getMischievousness(products);
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionSecond = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return calculateThirdObjectiveFunction(objectiveFunctionThird, coefficientsSecond, constraints,
						solutionSecond, products);
			case 3:
				coefficientsSecond = Product.getSumOfVitamins(products);
				objFunc = new LinearObjectiveFunction(coefficientsSecond, 0);
				try {
					solutionSecond = executeSimplexSolverAlgorithm(constraints, objFunc);
				} catch (NoFeasibleSolutionException e) {
					System.out.println("Brak rozwiazania!!!");
					constraints.remove(
							new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
					break;
				}
				return calculateThirdObjectiveFunction(objectiveFunctionThird, coefficientsSecond, constraints,
						solutionSecond, products);
			default:
				throw new IllegalArgumentException("Podana funkcja celu nie istnieje!");
			}

		}

		return solutionSecond;
	}

	private static PointValuePair calculateThirdObjectiveFunction(ObjectiveFunction objectiveFunctionThird,
			double[] coefficients, Collection<LinearConstraint> constraints, PointValuePair solution,
			HashMap<String, Product> products) {
		PointValuePair solutionThird = null;

		for (double epsilon = 1; epsilon <= 2; epsilon += 0.01) {
			System.out.println("Drugi warunek ogr.: " + epsilon);
			constraints.add(new LinearConstraint(coefficients, Relationship.LEQ, solution.getValue() * epsilon));
			LinearObjectiveFunction objFunc;
			double[] coefficientsThird;
			switch (objectiveFunctionThird.getValue()) {
			case 1:
				coefficientsThird = Product.getPrices(products);
				objFunc = new LinearObjectiveFunction(coefficientsThird, 0);
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
				coefficientsThird = Product.getMischievousness(products);
				objFunc = new LinearObjectiveFunction(coefficientsThird, 0);
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
				coefficientsThird = Product.getSumOfVitamins(products);
				objFunc = new LinearObjectiveFunction(coefficientsThird, 0);
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
	private static Collection<LinearConstraint> createConstraints(HashMap<Vitamin, Double> dailyDemandForVitamins,
			HashMap<Vitamin, Double> maxDailyDemandForVitamins, HashMap<String, Product> products) {
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();

		// Constraints - sum of vitamins must be greater than daily demand
		constraints.add(new LinearConstraint(Product.getVitamins(products, "A"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.A)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "D"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.D)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "E"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.E)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "K"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.K)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "B1"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.B1)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "B2"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.B2)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "B6"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.B6)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "B12"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.B12)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "C"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.C)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "PP"), Relationship.GEQ,
				dailyDemandForVitamins.get(Vitamin.PP)));

		// Constraints - sum of vitamins must be lower than max daily demand
		// constraints.add(new LinearConstraint(Product.getVitamins(products,
		// "A"), Relationship.LEQ,
		// maxDailyDemandForVitamins.get(Vitamin.A)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "D"), Relationship.LEQ,
				maxDailyDemandForVitamins.get(Vitamin.D)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "E"), Relationship.LEQ,
				maxDailyDemandForVitamins.get(Vitamin.E)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "B6"), Relationship.LEQ,
				maxDailyDemandForVitamins.get(Vitamin.B6)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "C"), Relationship.LEQ,
				maxDailyDemandForVitamins.get(Vitamin.C)));
		constraints.add(new LinearConstraint(Product.getVitamins(products, "PP"), Relationship.LEQ,
				maxDailyDemandForVitamins.get(Vitamin.PP)));

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
