def to_rna(dna):
    return str.translate(dna, str.maketrans("GCTA", "CGAU"))
