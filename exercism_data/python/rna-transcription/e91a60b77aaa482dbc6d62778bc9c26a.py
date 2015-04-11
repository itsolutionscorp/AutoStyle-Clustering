from string import maketrans

def to_rna(dna_sequence):
    pairing = maketrans("GCTA", "CGAU")
    return dna_sequence.translate(pairing)
