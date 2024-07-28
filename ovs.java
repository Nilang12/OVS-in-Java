import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Voter {
    private String name;
    private String vote;

    public Voter(String name) {
        this.name = name;
        this.vote = null;
    }

    public String getName() {
        return name;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}

class Candidate {
    private String name;
    private int votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void incrementVotes() {
        this.votes++;
    }
}

public class OnlineVotingSystem {
    private Map<String, Voter> voters;
    private Map<String, Candidate> candidates;

    public OnlineVotingSystem() {
        this.voters = new HashMap<>();
        this.candidates = new HashMap<>();
    }

    public void addVoter(String name) {
        voters.put(name, new Voter(name));
    }

    public void addCandidate(String name) {
        candidates.put(name, new Candidate(name));
    }

    public void castVote(String voterName, String candidateName) {
        if (voters.containsKey(voterName) && candidates.containsKey(candidateName)) {
            Voter voter = voters.get(voterName);
            if (voter.getVote() == null) {
                voter.setVote(candidateName);
                candidates.get(candidateName).incrementVotes();
                System.out.println("Vote cast successfully!");
            } else {
                System.out.println("You have already voted!");
            }
        } else {
            System.out.println("Invalid voter or candidate!");
        }
    }

    public void displayResults() {
        for (Candidate candidate : candidates.values()) {
            System.out.println(candidate.getName() + ": " + candidate.getVotes() + " votes");
        }
    }

    public static void main(String[] args) {
        OnlineVotingSystem votingSystem = new OnlineVotingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Voter");
            System.out.println("2. Add Candidate");
            System.out.println("3. Cast Vote");
            System.out.println("4. Display Results");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter voter name: ");
                    String voterName = scanner.nextLine();
                    votingSystem.addVoter(voterName);
                    break;
                case 2:
                    System.out.print("Enter candidate name: ");
                    String candidateName = scanner.nextLine();
                    votingSystem.addCandidate(candidateName);
                    break;
                case 3:
                    System.out.print("Enter voter name: ");
                    String voterNameForVote = scanner.nextLine();
                    System.out.print("Enter candidate name: ");
                    String candidateNameForVote = scanner.nextLine();
                    votingSystem.castVote(voterNameForVote, candidateNameForVote);
                    break;
                case 4:
                    votingSystem.displayResults();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}