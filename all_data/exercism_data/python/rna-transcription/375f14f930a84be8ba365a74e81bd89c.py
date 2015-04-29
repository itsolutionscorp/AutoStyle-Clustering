from itertools import imap

def convert_nucleotide(nucleotide):
    conversion_dict = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    return conversion_dict[nucleotide]

def to_rna(strand):
    return ''.join(imap(convert_nucleotide, strand))
