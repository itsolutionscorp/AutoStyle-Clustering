from string import maketrans

dna_to_rna_translation = maketrans("GCTA", "CGAU")

def to_rna(dna):
    rna = dna.translate(dna_to_rna_translation)
    return rna
