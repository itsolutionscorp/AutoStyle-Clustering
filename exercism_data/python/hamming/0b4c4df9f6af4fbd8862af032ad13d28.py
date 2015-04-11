def hamming(first, second):
    j = min(len(first), len(second))
    i = 0
    n = 0
    while i < j:
        n += 0 if first[i] == second[i] else 1
        i += 1
    return n + abs(len(first) - len(second))
