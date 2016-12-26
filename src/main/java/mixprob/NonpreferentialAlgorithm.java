package mixprob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

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

public class NonpreferentialAlgorithm {
	public static Result executeNonpreferentialAlgorithm(HashMap<Vitamin, Double> dailyDemandForVitamins,
			HashMap<Vitamin, Double> maxDailyDemandForVitamins, HashMap<String, Product> products) {
		double[] randomNumbers = null;
		Double distance = Double.MAX_VALUE;

		Collection<LinearConstraint> constraints = createConstraints(dailyDemandForVitamins, maxDailyDemandForVitamins,
				products);
		Collection<LinearObjectiveFunction> objectiveFunctions = createObjectiveFunctions(products);

		ArrayList<PointValuePair> algorithmSolutions = executeSimplexSolverAlgorithm(constraints, objectiveFunctions);

		double[] bestValues = chooseBestValues(algorithmSolutions);
		ArrayList<Double> bestSolutions = calculateObjectiveFunctions(bestValues, products);

		for (int i = 0; i < 1000000; i++) {
			double[] randomNumbersTemp = getRandomNumbers(products.size());
			ArrayList<Double> randomSolutions = calculateObjectiveFunctions(randomNumbersTemp, products);
			double distanceTemp = calculateDistanceBetweenSolutions(bestSolutions, randomSolutions);
			if (distanceTemp <= distance) {
				distance = distanceTemp;
				randomNumbers = randomNumbersTemp;
			}
		}

		System.out.println("Odleglosc: " + distance);
		Result results = new Result();
		try {
			results = new Result(randomNumbers);
		} catch (NullPointerException e) {
			throw new NoFeasibleSolutionException();
		}

		return results;
	}

	private static double[] getRandomNumbers(int size) {
		double[] randomNumbers = new double[size];
		int rangeMin = 0;
		int rangeMax = 100;

		for (int i = 0; i < size; i++) {
			Random r = new Random();
			randomNumbers[i] = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		}

		return randomNumbers;
	}

	private static ArrayList<Double> calculateObjectiveFunctions(double[] randomNumbers,
			HashMap<String, Product> products) {
		ArrayList<Double> randomSolutions = new ArrayList<Double>();

		Double firstRandomSolution = calculateFirstObjectiveFunction(randomNumbers, products);
		Double secondRandomSolution = calculateSecondObjectiveFunction(randomNumbers, products);
		Double thirdRandomSolution = calculateThirdObjectiveFunction(randomNumbers, products);

		randomSolutions.add(firstRandomSolution);
		randomSolutions.add(secondRandomSolution);
		randomSolutions.add(thirdRandomSolution);

		return randomSolutions;
	}

	private static Double calculateFirstObjectiveFunction(double[] randomNumbers, HashMap<String, Product> products) {
		Double randomSolutions = 0.0;
		double[] coefficients = Product.getPrices(products);

		for (int i = 0; i < coefficients.length; i++) {
			randomSolutions += coefficients[i] * randomNumbers[i];
		}

		return randomSolutions;
	}

	private static Double calculateSecondObjectiveFunction(double[] randomNumbers, HashMap<String, Product> products) {
		Double randomSolutions = 0.0;
		double[] coefficients = Product.getMischievousness(products);

		for (int i = 0; i < coefficients.length; i++) {
			randomSolutions += coefficients[i] * randomNumbers[i];
		}

		return randomSolutions;
	}

	private static Double calculateThirdObjectiveFunction(double[] randomNumbers, HashMap<String, Product> products) {
		Double randomSolutions = 0.0;
		double[] coefficients = Product.getSumOfVitamins(products);

		for (int i = 0; i < coefficients.length; i++) {
			randomSolutions += coefficients[i] * randomNumbers[i];
		}

		return randomSolutions;
	}

	private static double calculateDistanceBetweenSolutions(ArrayList<Double> bestSolutions,
			ArrayList<Double> randomSolutions) {
		double distance = 0.0;
		double[] point = new double[bestSolutions.size()];
		double[] point2 = new double[randomSolutions.size()];

		for (int i = 0; i < bestSolutions.size(); i++) {
			point[i] = bestSolutions.get(i);
			// System.out.print(point[i] + " ");
		}
		// System.out.println();

		for (int i = 0; i < randomSolutions.size(); i++) {
			point2[i] = randomSolutions.get(i);
			// System.out.print(point2[i] + " ");
		}
		// System.out.println();

		distance = Math.sqrt((Math.abs(point[0] - point2[0])) * (Math.abs(point[0] - point2[0]))
				+ (Math.abs(point[1] - point2[1])) * (Math.abs(point[1] - point2[1]))
				+ (Math.abs(point[2] - point2[2])) * (Math.abs(point[2] - point2[2])));

		return distance;
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
	 * Method for creating linear objective functions.
	 * 
	 * @return linear objective functions
	 */
	private static Collection<LinearObjectiveFunction> createObjectiveFunctions(HashMap<String, Product> products) {
		Collection<LinearObjectiveFunction> objectiveFunctions = new ArrayList<LinearObjectiveFunction>();

		objectiveFunctions.add(new LinearObjectiveFunction(Product.getPrices(products), 0));
		objectiveFunctions.add(new LinearObjectiveFunction(Product.getMischievousness(products), 0));
		objectiveFunctions.add(new LinearObjectiveFunction(Product.getSumOfVitamins(products), 0));

		return objectiveFunctions;
	}

	/**
	 * Method for executing SimplexSolver algorithm
	 * 
	 * @param constraints
	 *            - linear constraints for SimplexSolver algorithm
	 * @param objectiveFunctions
	 *            - linear objective functions for SimplexSolver algorithm
	 * @return solutions of objective functions with given constraints
	 */
	private static ArrayList<PointValuePair> executeSimplexSolverAlgorithm(Collection<LinearConstraint> constraints,
			Collection<LinearObjectiveFunction> objectiveFunctions) {
		ArrayList<PointValuePair> solutions = new ArrayList<PointValuePair>();
		SimplexSolver solver = new SimplexSolver();

		for (LinearObjectiveFunction objFunc : objectiveFunctions) {
			PointValuePair solution = solver.optimize(new MaxIter(100), objFunc, new LinearConstraintSet(constraints),
					GoalType.MINIMIZE, new NonNegativeConstraint(true));
			solutions.add(solution);
		}

		return solutions;
	}

	/**
	 * Method for choosing best (minimum) values from solutions of objective
	 * functions
	 * 
	 * @param solutions
	 *            - solutions of linear objective functions calculated by
	 *            SimplexSolver algorithm
	 * @return best (minimum) values from solutions of objective functions
	 */
	private static double[] chooseBestValues(ArrayList<PointValuePair> solutions) {
		double[] bestValues = new double[solutions.get(0).getPoint().length];
		double prod1 = Double.MAX_VALUE;
		double prod2 = Double.MAX_VALUE;
		double prod3 = Double.MAX_VALUE;
		double prod4 = Double.MAX_VALUE;
		double prod5 = Double.MAX_VALUE;
		double prod6 = Double.MAX_VALUE;

		for (PointValuePair solution : solutions) {
			if (solution.getPoint()[0] < prod1) {
				prod1 = solution.getPoint()[0];
			}
			if (solution.getPoint()[1] < prod2) {
				prod2 = solution.getPoint()[1];
			}
			if (solution.getPoint()[2] < prod3) {
				prod3 = solution.getPoint()[2];
			}
			if (solution.getPoint()[3] < prod4) {
				prod4 = solution.getPoint()[3];
			}
			if (solution.getPoint()[4] < prod5) {
				prod5 = solution.getPoint()[4];
			}
			if (solution.getPoint()[5] < prod6) {
				prod6 = solution.getPoint()[5];
			}
		}

		bestValues[0] = prod1;
		bestValues[1] = prod2;
		bestValues[2] = prod3;
		bestValues[3] = prod4;
		bestValues[4] = prod5;
		bestValues[5] = prod6;

		return bestValues;
	}
}
