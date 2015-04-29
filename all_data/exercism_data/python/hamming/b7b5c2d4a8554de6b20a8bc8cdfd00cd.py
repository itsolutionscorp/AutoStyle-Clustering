def distance(inp1, inp2):
    y1 = list(inp1)
    y2 = list(inp2)
    count = 0
    for i in range(len(y1)):
        if y1[i] != y2[i]:
            count += 1
    return count
