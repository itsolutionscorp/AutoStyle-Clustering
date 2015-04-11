complements = {'G' : 'C' , 'C' : 'G' , 'T' : 'A' , 'A' : 'U'}

def to_rna(dna):
    rna = ''
    for ncltd in dna:
        rna += complements[ncltd]
    return rna
