def hamming(first, second):
    difference = abs(len(first) - len(second))
    return sum([1 for x, y in zip(first, second) if x != y]) + difference
