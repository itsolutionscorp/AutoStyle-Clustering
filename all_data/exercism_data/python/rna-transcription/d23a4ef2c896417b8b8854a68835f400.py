from string import maketrans

def to_rna(dna):
    translation = maketrans("CGTA", "GCAU")
    return dna.translate(translation)
