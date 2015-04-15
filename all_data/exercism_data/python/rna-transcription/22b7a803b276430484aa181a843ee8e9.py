def to_rna(dna):
 
    dna_to_rna = {'G': 'C',
                  'C': 'G',
                  'T': 'A',
                  'A': 'U'}
    
    rna = [dna_to_rna[c] for c in dna]
    
    return "".join(rna)
    




        
