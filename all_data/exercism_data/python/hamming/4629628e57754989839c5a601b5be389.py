def distance(sample1, sample2):
    count = 0
    for i, c in enumerate(sample1):
        c2 = sample2[i]
        if c != c2:
            count += 1
    return count
