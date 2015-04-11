def to_rna(dna_strand):
    conversion = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
        }
    return ''.join([conversion[nucleotide] for nucleotide in dna_strand])
