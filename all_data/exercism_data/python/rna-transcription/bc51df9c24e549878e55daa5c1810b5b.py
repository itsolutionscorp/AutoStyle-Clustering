from string import translate, maketrans

dna2rna = maketrans("GCTA", "CGAU")

def to_rna(dna):
    return translate(dna, dna2rna)
