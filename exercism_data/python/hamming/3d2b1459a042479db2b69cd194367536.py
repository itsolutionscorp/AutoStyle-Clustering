def hamming(s1, s2):
    return sum(pair[0] != pair[1] for pair in zip(s1, s2)) + abs(len(s1) - len(s2))
