package de.uniaugsburg.isse.experiments;

import java.io.File;
import java.util.Date;

import de.uniaugsburg.isse.util.Utils;

/**
 * This class runs a number of identical experiments with differing random seeds
 * 
 * @author Alex Schiendorfer
 *
 */
public class ExperimentSeries {
	private long hierarchyRandomSeed[];
	private long avppsRandomSeeds[];
	private long initialStatesSeeds[];

	private Experiment experiment;
	private String symbolicName;

	public long[] getHierarchyRandomSeed() {
		return hierarchyRandomSeed;
	}

	public void setHierarchyRandomSeed(long[] hierarchyRandomSeed) {
		this.hierarchyRandomSeed = hierarchyRandomSeed;
	}

	public long[] getAvppsRandomSeeds() {
		return avppsRandomSeeds;
	}

	public void setAvppsRandomSeeds(long[] avppsRandomSeeds) {
		this.avppsRandomSeeds = avppsRandomSeeds;
	}

	public long[] getInitialStatesSeeds() {
		return initialStatesSeeds;
	}

	public void setInitialStatesSeeds(long[] initialStatesSeeds) {
		this.initialStatesSeeds = initialStatesSeeds;
	}

	public Experiment getExperiment() {
		return experiment;
	}

	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}

	public void run() {
		long timestamp = new Date().getTime();
		ExperimentStatistics statistics = new ExperimentStatistics();
		statistics.setSolveCentrally(experiment.isSolveCentrally());
		statistics.setSolveHierarchically(experiment.isSolveHierarchically());
		experiment.setStatistics(statistics);
		for (int i = 0; i < initialStatesSeeds.length; ++i) {
			experiment.setInitialStatesSeed(initialStatesSeeds[i]);
			experiment.setAvppsRandomSeed(avppsRandomSeeds[i]);
			experiment.setHierarchyRandomSeed(hierarchyRandomSeed[i]);
			experiment.run();
		}

		// write csv file
		String csvContent = statistics.writeCsv();
		String statsFileName = null;
		if (this.symbolicName == null) {
			statsFileName = "statscsv" + timestamp;
		} else
			statsFileName = this.symbolicName;

		File csvFile = new File("results/" + statsFileName + ".csv");
		Utils.writeFile(csvFile.getAbsolutePath(), csvContent);
	}

	public void setSymbolicName(String symbolicName) {
		this.symbolicName = symbolicName;
	}

}
