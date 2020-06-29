//Objective is to reconstruct a 2-D array of departures and destinations into
//a 1-D array of a list of consecutive cities for a trip. Two of the same
//destinations are sorted lexicographically

let tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]


//O(nlogn) solution that sorts all of the departures and destinations into a map.
//Destinations are then sorted lexicographically. Finally, a dfs traversal
//is done to find the order of cities.

let map = {}
let result = []

//Map all departures to their destinations
for (let ticket of tickets) {
    let departure = ticket[0]
    let destination = ticket[1]

    if (map[departure] == undefined) {
        map[departure] = [destination]
    } else {
        map[departure].push(destination)
    }
}

//Sort the destinations lexicographically
for (let departure in map) {
    map[departure].sort()
}

//DFS starting w/ 'JFK', we keep connecting our paths until we reach a place
//with no destination to go to
function dfs(node) {
    while (map[node] && map[node].length) {
        dfs(map[node].shift())
    }
    result.push(node)
}
dfs('JFK')

return result.reverse()