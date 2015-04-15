def to_rna(dna):
    mapping = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }

    rna = []
    for nucleotide in dna:
        rna.append(mapping[nucleotide])
    return ''.join(rna)
