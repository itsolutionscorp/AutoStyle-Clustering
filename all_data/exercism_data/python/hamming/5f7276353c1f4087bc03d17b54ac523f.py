def distance(seq1, seq2):
    count = 0
    for a, b in zip(seq1, seq2):
        if a != b:
            count += 1
    return count
