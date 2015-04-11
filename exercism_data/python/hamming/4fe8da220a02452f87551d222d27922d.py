def distance(seq1, seq2):
    if len(seq1) != len(seq2):
        return None
    hamming_distance = 0
    for p1, p2 in zip(seq1, seq2):
        if p1 != p2:
            hamming_distance += 1
    return hamming_distance
