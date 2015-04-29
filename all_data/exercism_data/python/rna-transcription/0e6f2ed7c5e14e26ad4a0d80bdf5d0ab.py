# vim:fileencoding=utf-8


DNA_NUCLEOTIDE_MAP = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U',
}


def to_rna(dna_nucleotides):
    return ''.join(map(_complementor, list(dna_nucleotides)))


def _complementor(char):
    return DNA_NUCLEOTIDE_MAP[char]
