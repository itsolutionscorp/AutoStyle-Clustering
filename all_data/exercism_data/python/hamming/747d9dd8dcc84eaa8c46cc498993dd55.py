def hamming(first, second):
    differences = 0
    lmin = min(len(first), len(second))
    lmax = max(len(first), len(second))
    for i in range(0, lmin):
        if first[i] != second[i]:
            differences += 1
    differences += lmax - lmin
    return differences
