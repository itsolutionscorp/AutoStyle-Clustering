compliments = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}

def to_rna(dna_str):
    return ''.join([compliments[nucleotide] for nucleotide in dna_str])
