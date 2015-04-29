def hamming(strandOne, strandTwo):
    return sum(p[0] != p[1] for p in map(None, strandOne, strandTwo))
