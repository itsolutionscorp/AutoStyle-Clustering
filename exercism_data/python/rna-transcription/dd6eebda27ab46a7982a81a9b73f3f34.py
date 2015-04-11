def to_rna(dna):
    change={'G':'C','C':'G','T':'A','A':'U'}
    rna=[]
    
    for item in dna:
        rna.append(change[item])
    return ''.join(rna)
