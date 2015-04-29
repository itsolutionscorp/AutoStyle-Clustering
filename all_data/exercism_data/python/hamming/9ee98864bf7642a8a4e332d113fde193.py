import itertools

def hamming(seq1, seq2):
    distance = abs(len(seq1) - len(seq2))
    for pair in itertools.izip(seq1, seq2):
        if pair[0] != pair[1]:
            distance += 1
    return distance
