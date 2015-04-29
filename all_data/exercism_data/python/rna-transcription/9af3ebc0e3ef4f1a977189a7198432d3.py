import string 

def to_rna(strand):
    return strand.translate(string.maketrans("AGTC", "UCAG"))
    
