

def distance(first, second):
    dist = 0
    for x, y in zip(first, second):
        if x != y:
            dist += 1
    return dist
