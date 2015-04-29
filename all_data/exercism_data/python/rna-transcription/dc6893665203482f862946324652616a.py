def to_rna(dna):
    map = {
        'G' : 'C',
        'C' : 'G',
        'T' : 'A',
        'A' : 'U'
    }
    return ''.join(map[nucleotide] for nucleotide in dna)
