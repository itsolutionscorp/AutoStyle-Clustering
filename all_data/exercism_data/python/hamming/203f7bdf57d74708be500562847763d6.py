def distance(sequence1, sequence2):
    hamming = 0
    if len(sequence1) == len(sequence2):
        for pos in xrange(len(sequence1)):
            if not sequence1[pos] == sequence2[pos]:
                hamming += 1
    
    return hamming
