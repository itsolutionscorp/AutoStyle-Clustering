DNA_TO_RNA = { 'A': 'U', 'C': 'G', 'G': 'C', 'T': 'A' }

def to_rna(strand):
    return ''.join(DNA_TO_RNA[nucleotide] for nucleotide in strand)
