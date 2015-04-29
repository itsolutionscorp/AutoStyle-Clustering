def hamming(string1, string2):
    return sum(1 for e1, e2 in zip(string1, string2) if e1!=e2) + abs(len(string1)-len(string2))
