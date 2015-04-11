def distance(a, b):
    distance = 0
    
    for (ca, cb) in zip(a, b):
        if ca != cb:
            distance += 1
    return distance
