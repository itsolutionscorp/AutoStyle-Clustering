dna_to_rna = (('g', 'c'), ('c', 'g'), ('t', 'a'), ('a', 'u'))

def to_rna(dna):
    rna = dna.upper()
    for dna_code, rna_code in dna_to_rna:    
        rna = rna.replace(dna_code.upper(), rna_code.lower())
    return rna.upper()
