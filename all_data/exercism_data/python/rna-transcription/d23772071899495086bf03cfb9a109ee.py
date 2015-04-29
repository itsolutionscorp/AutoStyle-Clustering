def to_rna(dna):
    rna = ""
    for i in range(0, len(dna)):
        if dna[i] == 'G':
            rna += 'C'
        elif dna[i] == 'C':
            rna += 'G'
        elif dna[i] == 'T':
            rna += 'A'
        else:
            rna += 'U'
    return rna
