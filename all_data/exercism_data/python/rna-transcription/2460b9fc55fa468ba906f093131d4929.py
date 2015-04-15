from string import maketrans

def to_rna(dna):
    rna = dna.translate(maketrans("GCTA","CGAU"))
    return rna
    
    
