from string import maketrans
def to_rna(nucleotide):
    trans = maketrans("GCTA", "CGAU")
    return nucleotide.translate(trans)
