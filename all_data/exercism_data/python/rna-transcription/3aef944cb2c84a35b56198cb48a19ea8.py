def to_rna(nucleotide):
    trantab = str.maketrans('GCAT', 'CGUA')
    return nucleotide.translate(trantab)
