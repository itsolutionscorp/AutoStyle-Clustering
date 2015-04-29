import string


def to_rna(dna):
    trans_table = string.maketrans("ACGT", "UGCA")
    return dna.translate(trans_table)
