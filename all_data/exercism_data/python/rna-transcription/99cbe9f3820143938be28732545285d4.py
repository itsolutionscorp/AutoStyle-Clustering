from string import maketrans

intab = 'GCTA'
outtab = 'CGAU'
trantab = maketrans(intab, outtab)

def to_rna(dna):    
    return dna.translate(trantab)
