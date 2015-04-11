from string import maketrans

def to_rna(dna):

    intab = "GCTA"
    outtab = "CGAU"
    transtab = maketrans(intab, outtab)

    return dna.translate(transtab);
