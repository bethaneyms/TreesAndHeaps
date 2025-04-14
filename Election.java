import java.util.*;

class Candidate {
    String name;
    int votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public void addVote() {
        votes++;
    }
}

class Election {
    private Map<String, Candidate> candidateMap;
    private PriorityQueue<Candidate> maxHeap;
    private int totalVotes;

    public Election() {
        candidateMap = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> b.votes - a.votes);
        totalVotes = 0;
    }

    public void initializeCandidates(LinkedList<String> candidates) {
        for (String name : candidates) {
            Candidate c = new Candidate(name);
            candidateMap.put(name, c);
            maxHeap.offer(c);
        }
    }

    public void castVote(String candidate) {
        if (!candidateMap.containsKey(candidate)) return;

        Candidate c = candidateMap.get(candidate);
        maxHeap.remove(c);
        c.addVote();
        maxHeap.offer(c);
        totalVotes++;
    }

    public void castRandomVote() {
        List<String> names = new ArrayList<>(candidateMap.keySet());
        Random rand = new Random();
        String randomCandidate = names.get(rand.nextInt(names.size()));
        castVote(randomCandidate);
    }

    public void rigElection(String winnerName) {
        if (!candidateMap.containsKey(winnerName)) return;

        for (Candidate c : candidateMap.values()) {
            c.votes = 0; // reset all votes
        }
        candidateMap.get(winnerName).votes = totalVotes;
        maxHeap.clear();
        maxHeap.addAll(candidateMap.values());
    }

    public List<String> getTopKCandidates(int k) {
        List<Candidate> sortedList = new ArrayList<>(maxHeap);
        sortedList.sort((a, b) -> b.votes - a.votes);

        List<String> topK = new ArrayList<>();
        for (int i = 0; i < Math.min(k, sortedList.size()); i++) {
            topK.add(sortedList.get(i).name);
        }
        return topK;
    }

    public void auditElection() {
        List<Candidate> sortedList = new ArrayList<>(maxHeap);
        sortedList.sort((a, b) -> b.votes - a.votes);

        for (Candidate c : sortedList) {
            System.out.println(c.name + " - " + c.votes);
        }
    }

    public void setTotalVotes(int p) {
        totalVotes = p;
    }
}
