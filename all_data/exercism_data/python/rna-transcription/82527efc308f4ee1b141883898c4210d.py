from string import maketrans, translate
def to_rna(dna):
    return translate(dna, maketrans('GCTA','CGAU'))

    
