def distance(sequence1, sequence2):
    if len(sequence1) != len(sequence2):
        raise Exception("DNA sequences must be the same length")
    distance_count = 0
    for i in xrange(len(sequence1)):
        if sequence1[i] != sequence2[i]:
            distance_count += 1
    return distance_count
