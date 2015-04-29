def to_rna(dna):
    complements = {
        'G' : 'C',
        'C' : 'G',
        'T' : 'A',
        'A' : 'U'
    }
    rna = []

    for nucleotide in dna:
        rna.append(complements[nucleotide])

    return ''.join(rna)
