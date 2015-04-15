def distance(inp1,inp2):
    return [x == y for (x,y) in zip(inp1,inp2)].count(False)
