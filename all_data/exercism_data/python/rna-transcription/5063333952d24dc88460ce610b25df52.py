def to_rna(dna):
    trans_table = str.maketrans('GCTA', 'CGAU')
    print(dna.translate(trans_table))
    return dna.translate(trans_table)
