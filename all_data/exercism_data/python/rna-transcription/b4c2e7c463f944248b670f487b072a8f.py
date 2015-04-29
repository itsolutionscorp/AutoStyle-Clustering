def to_rna(seq):
    trans = dict(zip('ACGT', 'UGCA'))
    return ''.join(trans[c] for c in seq)
