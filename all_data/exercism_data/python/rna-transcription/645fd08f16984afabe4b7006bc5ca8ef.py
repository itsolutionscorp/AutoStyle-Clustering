import string
def to_rna(dna):
    tr=string.maketrans("CGAT", "GCUA")
    return dna.translate(tr)
