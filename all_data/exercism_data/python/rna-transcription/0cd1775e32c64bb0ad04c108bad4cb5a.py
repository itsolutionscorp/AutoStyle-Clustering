from string import maketrans


def to_rna(dna):
    dna_in = "GCTA"
    rna_out = "CGAU"
    rna = maketrans(dna_in, rna_out)
    
    return dna.translate(rna)
