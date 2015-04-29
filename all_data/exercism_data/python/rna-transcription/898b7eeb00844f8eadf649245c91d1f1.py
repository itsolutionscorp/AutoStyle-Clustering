RNA_BY_DNA = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}


def to_rna(dna_sequence):
    return ''.join(RNA_BY_DNA[nucleotide] for nucleotide in dna_sequence)
