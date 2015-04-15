DNA_MAP = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}


def to_rna(sequence: str) -> str:
    return ''.join([DNA_MAP[nucleotide] for nucleotide in sequence])
