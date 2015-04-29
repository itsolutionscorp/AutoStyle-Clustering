def to_rna(dna):
    result = []
    dna_ntide = ['G', 'C', 'T', 'A']
    rna_ntide = ['C', 'G', 'A', 'U']
    for i, ntide in enumerate(dna):
        result.append(rna_ntide[dna_ntide.index(ntide)])
        
    return ''.join(result)
