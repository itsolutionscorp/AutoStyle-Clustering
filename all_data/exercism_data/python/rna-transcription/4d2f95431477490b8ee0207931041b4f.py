def to_rna(seq):
    trans = dict(zip('ATGC', 'UACG'))
    return "".join(trans[c] for c in seq)
