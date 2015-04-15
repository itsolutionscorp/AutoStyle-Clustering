from string import translate, maketrans

def to_rna(dna):
    dna2rna = maketrans("GCTA", "CGAU")
    return translate(dna, dna2rna)
