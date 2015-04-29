nucleotide_map = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}

def to_rna(nucleotides):
    return "".join([nucleotide_map[dna_nt] for dna_nt in nucleotides])
