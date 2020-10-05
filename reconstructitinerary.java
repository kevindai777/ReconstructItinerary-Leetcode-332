//Java Solution

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, LinkedList<String>> graph = new HashMap<>();
        
        for (int i = 0; i < tickets.size(); i++) {
            String departure = tickets.get(i).get(0);
            String destination = tickets.get(i).get(1);
            
            if (!graph.containsKey(departure)) {
                graph.put(departure, new LinkedList<String>());
                graph.get(departure).add(destination);
            } else {
                graph.get(departure).add(destination);
            }
        }
        
        for (Map.Entry<String, LinkedList<String>> entry : graph.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        List<String> result = new ArrayList<>();
        dfs("JFK", graph, result);
        
        List<String> reversed = new ArrayList<>();
        for (int i = result.size() - 1; i >= 0; i--) {
            reversed.add(result.get(i));
        }
        
        return reversed;
    }
    
    public void dfs(String node, Map<String, LinkedList<String>> graph, List<String> result) {
        while (graph.containsKey(node) && graph.get(node).size() > 0) {
            dfs(graph.get(node).removeFirst(), graph, result);
        }
        result.add(node);
    }
}