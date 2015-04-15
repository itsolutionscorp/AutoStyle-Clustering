def count(dna, nucleotide):
    if nucleotide not in ['A', 'C', 'G', 'T']:
        raise(ValueError, 'Nucleotide must be one of A, C, G or T')
    dna = list(dna)
    return len([a for a in dna if a == nucleotide])

def nucleotide_counts(dna):
    counts = {}
    for nucleotide in ['A', 'C', 'G', 'T']:
        counts[nucleotide] = count(dna, nucleotide)
    return counts
