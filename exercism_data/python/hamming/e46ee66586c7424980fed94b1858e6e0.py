def hamming(seqa, seqb):
    return sum(a != b for a, b in map(None, seqa, seqb))
