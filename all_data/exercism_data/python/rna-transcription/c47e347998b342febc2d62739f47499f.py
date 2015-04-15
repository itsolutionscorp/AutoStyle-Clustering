def to_rna(dna):
    rna = list(dna)
    i = 0
    while i < len(dna):
        if rna[i] == 'G': rna[i] = 'C'
        elif rna[i] == 'C': rna[i] = 'G'
        elif rna[i] == 'T': rna[i] = 'A'
        elif rna[i] == 'A': rna[i] = 'U'
        i += 1
    return ''.join(rna)
