def to_rna(dna):
    rna = ["X"] * len(dna)
    
    dna_to_rna = {'G': 'C',
                  'C': 'G',
                  'T': 'A',
                  'A': 'U'}
    
    for i in range(len(dna)):
        rna[i] = dna_to_rna.get(dna[i])
    
    return "".join(rna)
    




        
