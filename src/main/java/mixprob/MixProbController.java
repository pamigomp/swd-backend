package mixprob;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;

@RestController
public class MixProbController {
	private static final Logger LOGGER = Logger.getLogger(MixProbController.class.getName());

	@CrossOrigin(origins = "http://localhost:8383")
	@RequestMapping(value = "/mixprob", method = RequestMethod.POST)
	public Results multiobjectiveOptimization(@RequestBody InputData inputData) throws IOException {
		LOGGER.log(Level.INFO, "Getting daily demand for vitamins - START");
		inputData = getDailyDemandForVitamins(inputData);
		LOGGER.log(Level.INFO, "Getting daily demand for vitamins - END \n {0}", inputData.toString());

		LOGGER.log(Level.INFO, "Calculating daily demand for vitamins based on physical activity - START");
		HashMap<Vitamin, Double> dailyDemandForVitamins = calculateDailyDemandForVitaminsBaseOnPhysicalActivity(
				inputData);
		LOGGER.log(Level.INFO, "Calculating daily demand for vitamins based on physical activity - END \n {0}",
				dailyDemandForVitamins.toString());

		LOGGER.log(Level.INFO, "Creating constraints and objective functions - START");
		Collection<LinearConstraint> constraints = createConstraints(dailyDemandForVitamins);
		Collection<LinearObjectiveFunction> objectiveFunctions = createObjectiveFunctions();
		LOGGER.log(Level.INFO, "Creating constraints and objective functions - END");

		LOGGER.log(Level.INFO, "Executing SimplexSolver algorithm - START");
		ArrayList<PointValuePair> solutions = executeSimplexSolverAlgorithm(constraints, objectiveFunctions);
		LOGGER.log(Level.INFO, "Executing SimplexSolver algorithm - END");

		LOGGER.log(Level.INFO, "Executing SimplexSolver algorithm - START");
		ArrayList<PointValuePair> bestValues = chooseBestValues(solutions);
		LOGGER.log(Level.INFO, "Executing SimplexSolver algorithm - START");

		Results results = new Results();
		// TODO ...

		LOGGER.log(Level.INFO, results.toString());
		return results;
	}

	/**
	 * Method for getting daily demand for vitamins based on age and gender of
	 * person from csv file.
	 * 
	 * @param inputData
	 *            - the set of input data to update
	 * @return set of input data updated by daily demand for vitamins (min and
	 *         max values)
	 * @throws IOException
	 */
	private static InputData getDailyDemandForVitamins(InputData inputData) throws IOException {
		List<String[]> amounOfVitamins = getDataFromCSVFile();
		char male = 'M';
		char female = 'K';
		
		if (inputData.getGender() == male || inputData.getGender() == female) {
			if (isBetween(inputData.getAge(), 1, 3)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("1") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 4, 8)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("4") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 9, 13)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("9") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 14, 18)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("14") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 19, 30)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("19") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 31, 50)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("31") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 51, 70)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("51") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 71, 125)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("71") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else {
				throw new IllegalArgumentException("Wiek poza zakresem!");
			}
		} else {
			throw new IllegalArgumentException("Nieprawidlowa plec!");
		}

		return inputData;
	}

	/**
	 * Method for reading csv file.
	 * 
	 * @return parsed csv file
	 * @throws IOException
	 */
	private static List<String[]> getDataFromCSVFile() throws IOException {
		List<String[]> amountOfVitamins = new ArrayList<String[]>();

		CSVReader reader = new CSVReader(new FileReader("./dane.csv"), ';');
		amountOfVitamins = reader.readAll();
		reader.close();

		return amountOfVitamins;
	}

	/**
	 * Method for checking if given value is in range
	 * 
	 * @param x
	 *            - value to check if it is range
	 * @param lower
	 *            - lower value of range
	 * @param upper
	 *            - upper value of range
	 * @return true if {@code x} is in range, otherwise false
	 */
	private static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}

	/**
	 * Method for setting daily demand for vitamins
	 * 
	 * @param inputData
	 *            - the set of input data to update
	 * @param row
	 *            - row of csv file which is actually processed
	 * @return set of input data updated by daily demand for vitamins (min and
	 *         max values)
	 */
	private static InputData setDailyDemandForVitamins(InputData inputData, String[] row) {
		inputData.setDailyDemandVitaminA(Double.parseDouble(row[3]));
		inputData.setMaxDailyDemandVitaminA(Double.parseDouble(row[4]));
		inputData.setDailyDemandVitaminB6(Double.parseDouble(row[5]));
		inputData.setMaxDailyDemandVitaminB6(Double.parseDouble(row[6]));
		inputData.setDailyDemandVitaminC(Double.parseDouble(row[7]));
		inputData.setMaxDailyDemandVitaminC(Double.parseDouble(row[8]));
		inputData.setDailyDemandVitaminE(Double.parseDouble(row[9]));
		inputData.setMaxDailyDemandVitaminE(Double.parseDouble(row[10]));

		return inputData;
	}

	/**
	 * Method for calculating daily demand for vitamins based on value of
	 * physical activity of person.
	 * 
	 * @param inputData
	 *            - the set of input data
	 * @return map with daily demand for each vitamin
	 */
	private static HashMap<Vitamin, Double> calculateDailyDemandForVitaminsBaseOnPhysicalActivity(InputData inputData) {
		HashMap<Vitamin, Double> dailyDemandForVitamins = new HashMap<Vitamin, Double>();

		switch (inputData.getPhysicalActivity()) {
		case NONE:
			dailyDemandForVitamins.put(Vitamin.A, inputData.getDailyDemandVitaminA());
			dailyDemandForVitamins.put(Vitamin.B6, inputData.getDailyDemandVitaminB6());
			dailyDemandForVitamins.put(Vitamin.C, inputData.getDailyDemandVitaminC());
			dailyDemandForVitamins.put(Vitamin.E, inputData.getDailyDemandVitaminE());
			break;
		case LOW:
			dailyDemandForVitamins.put(Vitamin.A,
					calculateAverage(inputData.getDailyDemandVitaminA(), inputData.getMaxDailyDemandVitaminA()) / 2);
			dailyDemandForVitamins.put(Vitamin.B6,
					calculateAverage(inputData.getDailyDemandVitaminB6(), inputData.getMaxDailyDemandVitaminB6()) / 2);
			dailyDemandForVitamins.put(Vitamin.C,
					calculateAverage(inputData.getDailyDemandVitaminC(), inputData.getMaxDailyDemandVitaminC()) / 2);
			dailyDemandForVitamins.put(Vitamin.E,
					calculateAverage(inputData.getDailyDemandVitaminE(), inputData.getMaxDailyDemandVitaminE()) / 2);
			break;
		case AVERAGE:
			dailyDemandForVitamins.put(Vitamin.A,
					calculateAverage(inputData.getDailyDemandVitaminA(), inputData.getMaxDailyDemandVitaminA()));
			dailyDemandForVitamins.put(Vitamin.B6,
					calculateAverage(inputData.getDailyDemandVitaminB6(), inputData.getMaxDailyDemandVitaminB6()));
			dailyDemandForVitamins.put(Vitamin.C,
					calculateAverage(inputData.getDailyDemandVitaminC(), inputData.getMaxDailyDemandVitaminC()));
			dailyDemandForVitamins.put(Vitamin.E,
					calculateAverage(inputData.getDailyDemandVitaminE(), inputData.getMaxDailyDemandVitaminE()));
			break;
		case HIGH:
			dailyDemandForVitamins.put(Vitamin.A, inputData.getMaxDailyDemandVitaminA());
			dailyDemandForVitamins.put(Vitamin.B6, inputData.getMaxDailyDemandVitaminB6());
			dailyDemandForVitamins.put(Vitamin.C, inputData.getMaxDailyDemandVitaminC());
			dailyDemandForVitamins.put(Vitamin.E, inputData.getMaxDailyDemandVitaminE());
			break;
		default:
			throw new IllegalArgumentException("Nieprawidlowy poziom aktywnosci fizycznej!");
		}

		return dailyDemandForVitamins;
	}

	/**
	 * Method for calculating average value of given values
	 * 
	 * @param values
	 *            - values for which average will be calculated
	 * @return average value of given {@code values}
	 */
	private static double calculateAverage(double... values) {
		double sum = 0;

		for (double value : values) {
			sum = sum + value;
		}

		return sum / values.length;
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
	 * Method for creating linear objective functions.
	 * 
	 * @return linear objective functions
	 */
	private static Collection<LinearObjectiveFunction> createObjectiveFunctions() {
		Collection<LinearObjectiveFunction> objectiveFunctions = new ArrayList<LinearObjectiveFunction>();

		objectiveFunctions.add(new LinearObjectiveFunction(new double[] { 0.6, 0.5, 1.2, 3.5, 0.5, 0.3 }, 0));
		objectiveFunctions.add(new LinearObjectiveFunction(
				new double[] { Mischievousness.LOW.value, Mischievousness.LOW.value, Mischievousness.AVERAGE.value,
						Mischievousness.HIGH.value, Mischievousness.LOW.value, Mischievousness.LOW.value },
				0));
		objectiveFunctions
				.add(new LinearObjectiveFunction(new double[] { 0.8071, 0.844, 2.9557, 3.438, 53.5081, 11.7604 }, 0));

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
	private static ArrayList<PointValuePair> chooseBestValues(ArrayList<PointValuePair> solutions) {
		ArrayList<PointValuePair> bestValues = new ArrayList<PointValuePair>();

		// TODO Find minimum (best) values in optimalValues

		return bestValues;
	}
}
