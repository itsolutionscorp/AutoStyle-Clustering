# First create tranlation table from DNA to RNA
# using maketrans function
# then use string function translate to execute

from string import maketrans

def to_rna(dna):
    intran = "GCTA"
    outtran = "CGAU"
    trantab = maketrans(intran, outtran) # Creates translation table from DNA to RNA

    return dna.translate(trantab)
