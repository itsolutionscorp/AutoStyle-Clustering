def to_rna(dna):
    translations = {'A': 'U', 'C': 'G', 'T': 'A', 'G': 'C'}
    dna = list(dna.upper())
    for i in range(len(dna)):
        dna[i] = translations[dna[i]]
    return ''.join(dna)
