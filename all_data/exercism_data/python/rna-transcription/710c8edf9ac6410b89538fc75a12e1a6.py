#!/usr/bin/python3


def to_rna(sequence):
    transcribe = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    rna = [transcribe[nucl] for nucl in sequence.upper()]
    return ''.join(rna)
