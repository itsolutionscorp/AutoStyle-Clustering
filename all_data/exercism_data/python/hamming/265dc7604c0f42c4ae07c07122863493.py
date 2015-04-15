def distance(seq1, seq2):
    return sum([s1 != s2 for s1,s2 in zip(seq1,seq2)])
