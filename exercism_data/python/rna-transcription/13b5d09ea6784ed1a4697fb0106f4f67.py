import string

def to_rna(dna):
    dna_to_rna = string.maketrans('GCTA', 'CGAU')
    return string.translate(dna, dna_to_rna)
