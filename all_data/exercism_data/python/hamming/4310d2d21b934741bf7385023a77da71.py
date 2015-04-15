def distance(strandA, strandB):
    """
    Counts the number of differences between two homologous DNA strands
    """
    if len(strandA) != len(strandB):
        raise Exception("strands of different length are not supported")

    dist = 0
    for i in xrange(len(strandA)):
        if strandA[i] != strandB[i]:
            dist += 1
    return dist
