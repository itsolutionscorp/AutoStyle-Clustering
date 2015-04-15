dna_to_rna_dict = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(dna):
    return ''.join([dna_to_rna_dict[nucleotide] for nucleotide in dna])
