def to_rna(dna):
    rna = ''
    for i in range(len(dna)):
        if dna[i] == 'G':
            rna = rna + 'C'
        elif dna[i] == 'C':
            rna = rna + 'G'
        elif dna[i] == 'T':
            rna = rna + 'A'
        elif dna[i] == 'A':
            rna = rna + 'U'
    return rna
