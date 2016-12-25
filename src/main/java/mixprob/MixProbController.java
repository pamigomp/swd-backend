package mixprob;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MixProbController {
	private static final Logger LOGGER = Logger.getLogger(MixProbController.class.getName());

	@CrossOrigin
	@RequestMapping(value = "/mixprob", method = RequestMethod.POST)
	public ArrayList<Result> multiobjectiveOptimization(@RequestBody InputData inputData) throws IOException {
		LOGGER.log(Level.INFO, "Create list of products - START");
		HashMap<String, Product> products = Product.createProducts();
		LOGGER.log(Level.INFO, "Create list of products - END \n {0}", products.toString());

		LOGGER.log(Level.INFO, "Getting daily demand for vitamins - START");
		inputData.getDailyDemandForVitamins(inputData);
		LOGGER.log(Level.INFO, "Getting daily demand for vitamins - END \n {0}", inputData.toString());

		LOGGER.log(Level.INFO, "Calculating daily demand for vitamins based on physical activity - START");
		HashMap<Vitamin, Double> dailyDemandForVitamins = inputData
				.calculateDailyDemandForVitaminsBaseOnPhysicalActivity(inputData);
		LOGGER.log(Level.INFO, "Calculating daily demand for vitamins based on physical activity - END \n {0}",
				dailyDemandForVitamins.toString());

		LOGGER.log(Level.INFO, "Calculating max daily demand for vitamins based on physical activity - START");
		HashMap<Vitamin, Double> maxDailyDemandForVitamins = inputData
				.calculateMaxDailyDemandForVitaminsBaseOnPhysicalActivity(inputData);
		LOGGER.log(Level.INFO, "Calculating max daily demand for vitamins based on physical activity - END \n {0}",
				maxDailyDemandForVitamins.toString());

		LOGGER.log(Level.INFO, "Executing apriori algorithm - START");
		Result aprioriAlgorithmResults = AprioriAlgorithm.executeAprioriAlgorithm(inputData.getObjectiveFunctions(),
				dailyDemandForVitamins, maxDailyDemandForVitamins, products);
		LOGGER.log(Level.INFO, "Executing apriori algorithm - END \n {0}", aprioriAlgorithmResults.toString());

		LOGGER.log(Level.INFO, "Executing non-preferential algorithm - START");
		Result nonpreferentialAlgorithmResults = NonpreferentialAlgorithm
				.executeNonpreferentialAlgorithm(dailyDemandForVitamins, maxDailyDemandForVitamins, products);
		LOGGER.log(Level.INFO, "Executing non-preferential algorithm - END \n {0}",
				nonpreferentialAlgorithmResults.toString());

		ArrayList<Result> results = new ArrayList<Result>();
		results.add(aprioriAlgorithmResults);
		results.add(nonpreferentialAlgorithmResults);

		return results;
	}
}
