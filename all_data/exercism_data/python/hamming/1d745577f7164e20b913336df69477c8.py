def hamming(s1, s2):
    difference = max(len(s1), len(s2)) - min(len(s1), len(s2))
    for x in range(min(len(s1), len(s2))):
        if s1[x] != s2[x]:
            difference += 1

    return difference
