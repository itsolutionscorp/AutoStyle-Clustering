def to_rna(strand):
    return strand.translate(str.maketrans('GCTA', 'CGAU'))
