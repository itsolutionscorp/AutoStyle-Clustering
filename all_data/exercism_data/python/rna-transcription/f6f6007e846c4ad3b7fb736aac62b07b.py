def to_rna(dna):
    trans_table = str.maketrans('GCTA', 'CGAU')
    return dna.translate(trans_table)
