def to_rna(dna):
    import string
    return dna.translate(string.maketrans('GCTA', 'CGAU'))
