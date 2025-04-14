import java.util.LinkedList;
import java.util.Arrays;
import java.util.*;

public class ElectionSystem {
    private static final List<String> NAME_POOL = Arrays.asList(
            "Marcus Fenix", "Dominic Santiago", "Damon Baird", "Cole Train",
            "Anya Stroud", "Sam Byrne", "Tai Kaliso", "Jace Stratton", "Clayton Carmine"
    );

    public static void main(String[] args) {
        Random rand = new Random();

        int numCandidates = rand.nextInt(5) + 3;
        Collections.shuffle(NAME_POOL);
        LinkedList<String> candidates = new LinkedList<>(NAME_POOL.subList(0, numCandidates));

        int p = rand.nextInt(16) + 5;

        System.out.println("Candidates: " + candidates);
        System.out.println("Total Votes to Cast: " + p);

        Election election = new Election();
        election.initializeCandidates(candidates);
        election.setTotalVotes(p);

        for (int i = 0; i < p; i++) {
            election.castRandomVote();
        }

        System.out.println("Top 3 candidates after voting:");
        System.out.println(election.getTopKCandidates(3));

        String winner = candidates.get(rand.nextInt(candidates.size()));
        System.out.println("Rigging election for: " + winner);
        election.rigElection(winner);

        System.out.println("Top 3 candidates after rigging:");
        System.out.println(election.getTopKCandidates(3));

        System.out.println("Final Audit:");
        election.auditElection();
    }
}
