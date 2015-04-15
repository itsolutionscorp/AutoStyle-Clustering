def distance(strandA, strandB):
    return sum(baseA != baseB for baseA, baseB in zip(strandA, strandB))
