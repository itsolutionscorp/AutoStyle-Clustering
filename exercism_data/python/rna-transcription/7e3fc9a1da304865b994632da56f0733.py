def to_rna(dna_in):
    
    dna_to_rna={'A':'U','C':'G','G':'C','T':'A'}
    temp = list(dna_in)
    
    for idx,c in enumerate(temp):
        temp[idx] = dna_to_rna[c];
        
    return ''.join(temp)
