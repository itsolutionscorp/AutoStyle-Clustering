def to_rna(dna):
    nucleotides = { 'C': 'G',
                    'G': 'C',
                    'A': 'U',
                    'T': 'A' }
    rna = ''
    for ch in dna:
        rna = rna + nucleotides[ch]
    return(rna)
