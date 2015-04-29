def to_rna(dna):
    trans = {'G': 'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = ""
    
    for nucleo in dna:
        rna = rna + trans[nucleo]
    
    return rna
