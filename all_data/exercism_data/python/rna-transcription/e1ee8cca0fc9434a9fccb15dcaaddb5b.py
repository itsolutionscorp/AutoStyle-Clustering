from string import translate, maketrans

def to_rna(dna):
    return translate(dna, maketrans("GCTA", "CGAU"))
