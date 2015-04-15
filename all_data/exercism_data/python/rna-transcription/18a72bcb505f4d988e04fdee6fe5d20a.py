from string import maketrans


def to_rna(dna):

    inDNA = "GCTA"
    inRNA = "CGAU"
    transdata = maketrans(inDNA, inRNA)
    dna = dna.translate(transdata);
    return dna
