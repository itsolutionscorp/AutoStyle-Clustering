def to_rna(x):
    rna = []
    for i in x:
        if i == 'G':
            rna.append('C')
        elif i == 'C':
            rna.append('G')
        elif i == 'T':
            rna.append('A')
        elif i == 'A':
            rna.append('U')

    return ''.join(rna)
