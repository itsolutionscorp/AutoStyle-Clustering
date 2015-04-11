def to_rna(strand):
    dna = "ATCG"
    rna = "UAGC"
    return strand.translate(str.maketrans(dna, rna))
