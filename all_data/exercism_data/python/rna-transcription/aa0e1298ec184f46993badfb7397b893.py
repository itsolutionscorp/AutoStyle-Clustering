from string import maketrans

def to_rna(dna):
    """changes dna to rna""" 
    trans = maketrans("GCTA", "CGAU")
    return dna.translate(trans)
