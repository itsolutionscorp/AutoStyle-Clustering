from itertools import izip_longest

def hamming(seq1, seq2):
    ham_dist = 0
    for ch1, ch2 in izip_longest(seq1, seq2):
        if ch1 != ch2:
            ham_dist += 1
    return ham_dist
