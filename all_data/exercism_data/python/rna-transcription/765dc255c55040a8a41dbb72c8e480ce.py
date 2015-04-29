__author__ = 'octowl'

dna2rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
transcript = lambda x: dna2rna[x]


def to_rna(dna):
    return ''.join(map(transcript, list(dna)))
