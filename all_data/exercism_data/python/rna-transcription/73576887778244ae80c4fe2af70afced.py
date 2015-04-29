def to_rna(seq):
    complements = {
            'G':'C',
            'C':'G',
            'T':'A',
            'A':'U'
            }
    return(''.join(map(
        lambda n: complements[n],
        seq)))
