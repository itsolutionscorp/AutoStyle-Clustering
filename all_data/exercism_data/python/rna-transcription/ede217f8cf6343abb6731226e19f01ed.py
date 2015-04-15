import string

trans_table = string.maketrans("GCTA","CGAU")

def to_rna(dna):
    return dna.translate(trans_table)
