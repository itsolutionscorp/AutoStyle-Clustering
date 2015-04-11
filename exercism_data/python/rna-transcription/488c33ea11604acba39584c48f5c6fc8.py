def to_rna(dna):
    nucleotides = {'G': 'C',
                   'C': 'G',
                   'T': 'A',
                   'A': 'U'}
    rna = ''
    for char in dna:
        rna = rna + nucleotides[char]
    return rna
