#Create a translation table, and return the translation
def to_rna(dna):
    rna = str.maketrans("GCTA","CGAU")
    return(dna.translate(rna))

    
