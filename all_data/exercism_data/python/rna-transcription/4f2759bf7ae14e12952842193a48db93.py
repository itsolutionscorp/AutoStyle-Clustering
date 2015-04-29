from string import maketrans


def to_rna(dna_strand):
    bases = "ATGC"
    compl_bases = "UACG"
    table = maketrans(bases, compl_bases)
    return dna_strand.translate(table)
