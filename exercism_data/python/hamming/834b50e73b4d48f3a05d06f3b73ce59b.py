def distance(a, b):
    distance = 0
    if len(a) != len(b):
        raise Error('Hamming distance is not defined for sequences of non-equal length')
    for i, j in zip(a, b):
        if i != j:
            distance += 1
    return distance
