#!/usr/bin/python3


def to_rna(sequence):
    transcribe = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }
    rna = []
    for nucl in sequence.upper():
        rna.append(transcribe[nucl])
    return ''.join(rna)
