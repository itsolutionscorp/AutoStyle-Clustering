from string import maketrans

def to_rna(dna_string):
    dna = "GCTA"
    rna = "CGAU"
    return dna_string.translate(maketrans(dna, rna))
