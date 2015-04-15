#!/usr/bin/python
def hamming(seq1, seq2):
    seq = "{:<" + str(max(len(seq1), len(seq2))) + "}"
    return sum([0 if a == b else 1 for a, b in zip(seq.format(seq1), seq.format(seq2))])
