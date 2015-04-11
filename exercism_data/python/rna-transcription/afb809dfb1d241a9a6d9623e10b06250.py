def to_rna(dna):
    # create a dictionary as a lookup table
    # map characters through this dictionary
    return dna.translate(str.maketrans('GCTA', 'CGAU'))

