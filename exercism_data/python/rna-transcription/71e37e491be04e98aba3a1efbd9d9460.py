from string import maketrans

#This function takes a DNA strand and returns the RNA compliment
def to_rna(strand):
    return strand.translate(maketrans('GCTA','CGAU'))
