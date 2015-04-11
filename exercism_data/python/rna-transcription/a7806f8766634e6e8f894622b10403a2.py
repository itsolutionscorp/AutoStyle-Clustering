def to_rna(dna):
    #conv = [('G','C'), ('C','G'), ('T','A'), ('A','U')]
    dna1 = ['G','C','T','A']
    rna1 = ['C','G','A','U']
    l = list(dna)
    rnalist = []
    for i in l:
        index = [j for j in range(4) if (i == dna1[j])]
        rnalist.append(rna1[index[0]])
    return ''.join(str(e) for e in rnalist)
