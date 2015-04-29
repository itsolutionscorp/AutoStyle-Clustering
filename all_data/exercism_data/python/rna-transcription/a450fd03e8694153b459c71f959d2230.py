def to_rna(dna):
    return dna.translate(dna.maketrans('GCTA', 'CGAU'))
