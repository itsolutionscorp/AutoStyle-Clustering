LOOKUP = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}


def to_rna(strand_in):
    strand_out = ''
    for nucleotide in strand_in:
        strand_out += LOOKUP[nucleotide]
    return strand_out
