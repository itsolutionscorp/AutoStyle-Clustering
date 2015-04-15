from itertools import starmap, zip_longest

def hamming(seq1, seq2):
    return(sum(starmap(
            lambda a, b: 1 if a!=b else 0,
            zip_longest(seq1, seq2))))
