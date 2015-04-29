def distance(a, b):
    distance = 0
    for (c, d) in zip(a, b):
        if c != d:
            distance += 1
    return distance
            
