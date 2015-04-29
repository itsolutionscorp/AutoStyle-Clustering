def hamming(seq1, seq2):
    seq1, seq2 = ensure_same_length(seq1, seq2)
    return sum(n1 != n2 for n1, n2 in zip(seq1, seq2))

def ensure_same_length(seq1, seq2):
    diff = len(seq2) - len(seq1)
    if diff > 0:
        seq1 += 'X'*diff
    elif diff < 0:
        seq2 += 'X'*abs(diff)
    return seq1, seq2
