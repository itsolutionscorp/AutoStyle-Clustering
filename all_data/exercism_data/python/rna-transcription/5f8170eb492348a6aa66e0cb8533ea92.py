def to_rna(dna):
    trans = str.maketrans('GCTA', 'CGAU')
    return dna.translate(trans)
