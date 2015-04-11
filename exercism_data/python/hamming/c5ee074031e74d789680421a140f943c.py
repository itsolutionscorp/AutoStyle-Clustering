def distance(a,b):
    hamming_length = 0
    for i, j in zip(a,b):
        if i != j:
            hamming_length += 1
    return hamming_length
