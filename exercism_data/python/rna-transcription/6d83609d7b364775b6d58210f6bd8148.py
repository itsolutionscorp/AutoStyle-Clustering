from string import maketrans


def to_rna(seq):
    transtable = maketrans('GCTA', 'CGAU')
    return seq.translate(transtable)
