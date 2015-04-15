import string

def to_rna(dna):
    dna_to_rna_table = string.maketrans('GCTA', 'CGAU')
    return dna.translate(dna_to_rna_table)
