def distance(seq1, seq2):
    dist = 0
    # Nifty looping technique from Python docs!
    for c1, c2 in zip(seq1, seq2):
        if c1 != c2:
            dist += 1
    return dist
