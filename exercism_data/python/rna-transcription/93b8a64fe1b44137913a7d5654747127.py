import string
to_rna_table = string.maketrans('GCTA', 'CGAU')

def to_rna(dna):
    return dna.translate(to_rna_table)
