import numpy as np

def distance(seq1, seq2):
    seq1 = np.fromstring(seq1, dtype='|S1')
    seq2 = np.fromstring(seq2, dtype='|S1')
    return np.sum(seq1 != seq2)
