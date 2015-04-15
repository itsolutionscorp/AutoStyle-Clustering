def distance(first, second):
    hamming_distance = 0
    for t, f in map(None, first, second):
        if t != f:
            hamming_distance += 1
    return hamming_distance
