
public class Team implements Comparable<Team>{
	private String name;
	private int noOfPoints;
	private int goalsConceded;
	private int goalsScored;
	private int noOfWins;
	private int noOfDraws;
	private int noOfLoses;
	
	public Team (String name) {
		this.name = name;
		this.goalsConceded = 0;
		this.goalsScored = 0;
		this.noOfPoints = 0;
		this.noOfWins = 0;
		this.noOfDraws = 0;
		this.noOfLoses = 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNoOfPoints() {
		return noOfPoints;
	}

	public void setNoOfPoints(int noOfPoints) {
		this.noOfPoints += noOfPoints;
	}

	public int getGoalsConceded() {
		return goalsConceded;
	}

	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded += goalsConceded;
	}
	
	public int getGoalsScored() {
		return goalsScored;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored += goalsScored;
	}
	
	public int getGoalDifference() {
		return this.goalsScored - this.goalsConceded;
	}
	
	public int getNoOfWins() {
		return noOfWins;
	}

	public void setNoOfWins(int noOfWins) {
		this.noOfWins += noOfWins;
	}

	public int getNoOfDraws() {
		return noOfDraws;
	}

	public void setNoOfDraws(int noOfDraws) {
		this.noOfDraws += noOfDraws;
	}

	public int getNoOfLoses() {
		return noOfLoses;
	}

	public void setNoOfLoses(int noOfLoses) {
		this.noOfLoses += noOfLoses;
	}

	@Override
	public int compareTo(Team o) {
		if(this.noOfPoints != o.getNoOfPoints()) {
			return Integer.compare(o.getNoOfPoints(), this.noOfPoints);
		}
		if (this.getGoalDifference() != o.getGoalDifference()) {
			return Integer.compare(o.getGoalDifference(), this.getGoalDifference());
		}
		if (this.noOfWins != o.getNoOfWins()) {
			return Integer.compare(o.getNoOfWins(), this.noOfWins);
		}
		return this.name.compareTo(o.getName());
	}
	
	
	
	
	

}
