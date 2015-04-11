def hamming(a, b):
    return len(filter(lambda x: x[0] != x[1], zip(a, b))) + abs(len(a) - len(b))
