def to_rna(dna):
    dna_map = {'G':'C','C':'G','T':'A','A':'U'}
    return ''.join([dna_map[x] for x in dna])
             
    
