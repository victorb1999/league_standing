import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Standing {
	private List<Team> allTeams;
	private String name;
	private Map<String, Team> mapByName;
	
	public Standing(String name, int noOfTeams) {
		this.name = name;
		allTeams = new ArrayList<>(noOfTeams);
		mapByName = new HashMap<>();
	}

	public List<Team> getAllTeams() {
		return allTeams;
	}

	public void setAllTeams(List<Team> allTeams) {
		this.allTeams = allTeams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void updateStanding() {
		Collections.sort(allTeams);
	}
	
	public void createMapByName() {
		for (Team toAdd : allTeams) {
			this.mapByName.put(toAdd.getName(), toAdd);
		}
	}
	
	public Team getTeam(String name) {
		return this.mapByName.get(name);
	}
	
	public void printStanding() {
		updateStanding();
		int position = 1;
		for(Team first : allTeams) {
			if(first.getName().equals("Skip")) {
				continue;
			}
			System.out.println(position + ". " + first.getName() + " Points:" + first.getNoOfPoints() 
								+ " GS:" + first.getGoalsScored() + " GC:" + first.getGoalsConceded()
								+ " GD:" + first.getGoalDifference() + " W:" + first.getNoOfWins()
								+ " D:" + first.getNoOfDraws() + " L:" +first.getNoOfLoses());
			position++;
		}
		System.out.println();
	}
	
	

}
