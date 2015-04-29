def distance(sample1, sample2):
    count = 0
    for e in range(len(sample1)):
        if sample1[e] != sample2[e]:
            count += 1
    return count
