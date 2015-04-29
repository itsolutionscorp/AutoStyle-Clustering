def distance(left, right):
    if len(left) != len(right):
        raise "Hamming distance not defined for strings of unequal length."
    dist = 0
    for i in range(len(left)):
        if left[i] != right[i]:
            dist += 1
    return dist
