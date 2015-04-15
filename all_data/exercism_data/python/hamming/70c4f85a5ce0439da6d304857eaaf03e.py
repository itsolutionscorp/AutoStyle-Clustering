def distance(sequence1, sequence2):
    differences = 0
    for n in range(len(sequence1)):
        if sequence1[n] != sequence2[n]:
            differences += 1
    return differences
