from string import maketrans

def to_rna(dna_string):
    dna = "GCTA"
    rna = "CGAU"
    result = ""

    result = dna_string.translate(maketrans(dna, rna))
    return result
