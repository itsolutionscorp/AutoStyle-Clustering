__author__ = 'woodward'


def hamming(seq1, seq2):
    hamm_diff = 0
    for val1, val2 in map(None, seq1, seq2):
        if val1 != val2:
            print val1, val2
            hamm_diff += 1
    return hamm_diff
