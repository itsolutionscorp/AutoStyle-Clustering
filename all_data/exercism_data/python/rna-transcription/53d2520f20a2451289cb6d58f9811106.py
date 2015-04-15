def to_rna(dna):
    
    dna = list(dna)
    rna=[]
    for i in dna:
        if i == 'G':
            rna.append('C')    
        elif i == 'C':
            rna.append('G')
        elif i == 'T':
            rna.append('A')
        elif i == 'A':
            rna.append('U')
    
    return ''.join(rna)            
