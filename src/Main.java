import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter the name of the championship: ");
		String name = sc.nextLine();
		System.out.println("Please enter the number of teams: ");
		int number = verifyInt(sc);
		sc.nextLine();
		
		Standing std = new Standing(name, number);
		if (number % 2 != 0) {
			number++;
			std.getAllTeams().add(new Team("Skip"));
			System.out.println("Please enter the name for every team: ");
			for (int i = 0; i < number - 1; i++) {
				String teamName = sc.nextLine();
				std.getAllTeams().add(new Team(teamName));
			}
		} else {
			System.out.println("Please enter the name for every team: ");
			for (int i = 0; i < number; i++) {
				String teamName = sc.nextLine();
				std.getAllTeams().add(new Team(teamName));
			}
		}
		std.createMapByName();
		
		List<Team> teams = new ArrayList<>(std.getAllTeams());
		
		List<List<String>> firstPart = generateRound(teams, std);
		reverseRound(firstPart, std, number);
		
		System.out.println(std.getName());
		System.out.println("Final Standing: ");
		std.printStanding();
		System.out.println("Champion: " + std.getAllTeams().get(0).getName());
		sc.close();
		
	}
	
	public static List<List<String>> generateRound(List<Team> teams, Standing std){
		int n = teams.size();
		List<List<String>> rounds = new ArrayList<>();
		
		List<Team> rotate = new ArrayList<>(teams);
		Team fixed = rotate.remove(0);
		int numberOfRounds = n - 1;
		
		for (int round = 1; round <= numberOfRounds; round++) {
			List<String> matches = new ArrayList<String>();
			matches.add(fixed.getName() + " vs " + rotate.get(0).getName());
			System.out.println("Round " + round + ":");
			
			if (!fixed.getName().equals("Skip") && !rotate.get(0).getName().equals("Skip")) {
				System.out.println(fixed.getName() + " vs " + rotate.get(0).getName());
				calculateResult(fixed, rotate.get(0));
			}
			
			
			for (int i = 1; i < rotate.size() / 2 + 1; i++) {
				Team team1 = rotate.get(i);
				Team team2 = rotate.get(rotate.size() - i);
				if(team1.getName().equals("Skip") || team2.getName().equals("Skip")) {
					continue;
				}
				matches.add(team1.getName() + " vs " + team2.getName());
				System.out.println(team1.getName() + " vs " + team2.getName());
				
				calculateResult(team1, team2);
			}
			
			rounds.add(matches);
			std.printStanding();
			
			Collections.rotate(rotate, 1);
		}
		
		return rounds;
	}
	
	public static void reverseRound(List<List<String>> firstPart, Standing std, int noRound){
		for (List<String> round : firstPart) {
			System.out.println("Round " + noRound + ":");
			for (String match : round) {
				String[] separate = match.split(" vs ");
				if (separate[0].equals("Skip") || separate[1].equals("Skip")) {
					continue;
				}
				Team team1 = std.getTeam(separate[1]);
				Team team2 = std.getTeam(separate[0]);
				
				System.out.println(team1.getName() + " vs " + team2.getName());
				calculateResult(team1, team2);
			}
			std.printStanding();
			noRound++;
		}
		
	}
	
	public static void calculateResult(Team a, Team b) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Goals scored by " + a.getName() + " :");
		int goalsForA = verifyInt(sc);
		System.out.println("Goals scored by " + b.getName() + " :");		
		int goalsForB = verifyInt(sc);
		System.out.println();
		
		a.setGoalsScored(goalsForA);
		a.setGoalsConceded(goalsForB);
		
		b.setGoalsScored(goalsForB);
		b.setGoalsConceded(goalsForA);
		
		if (goalsForA > goalsForB) {
			a.setNoOfPoints(3);
			a.setNoOfWins(1);
			b.setNoOfLoses(1);
		} else if (goalsForA == goalsForB) {			
			a.setNoOfPoints(1);
			b.setNoOfPoints(1);
			
			a.setNoOfDraws(1);
			b.setNoOfDraws(1);
		} else {
			b.setNoOfPoints(3);
			b.setNoOfWins(1);
			a.setNoOfLoses(1);
		}
		sc.close();
	}
	
	public static int verifyInt(Scanner sc) {
		while(true) {
			try {
				return sc.nextInt();
			} catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("You enter an invalid value. Try again: ");
				
			}
		}

	}
}
