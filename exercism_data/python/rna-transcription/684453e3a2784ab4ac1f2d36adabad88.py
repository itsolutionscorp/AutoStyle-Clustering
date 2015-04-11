"""
dna.py: Returns RNA compliment for given DNA strand.
"""

DNA = ['G', 'C', 'T', 'A']
RNA = ['C', 'G', 'A', 'U']


def to_rna(strand):
    nucleotide_lst = list(strand)
    nucleotide_dict = dict(zip(DNA, RNA))
    comparison = []
    for nucleotide in nucleotide_lst:
        for k, v in nucleotide_dict.items():
            if nucleotide == k:
                comparison.append(v)
    return ''.join(comparison)
