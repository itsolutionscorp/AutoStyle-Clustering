def to_rna(rna):
    intab = "GCTA"
    outtab = "CGAU"
    trantab = str.maketrans(intab, outtab)
    return rna.translate(trantab)
