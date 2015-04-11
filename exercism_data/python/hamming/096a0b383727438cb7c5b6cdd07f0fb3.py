def hamming(first, second):
    differences = abs(len(first) - len(second))
    for f, s in zip(first, second):
        if f != s:
            differences += 1
    return differences
