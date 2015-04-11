#!/usr/bin/env python

def to_rna(dna):
    transcription = {
        'A': 'U',
        'C': 'G',
        'G': 'C',
        'T': 'A'
    }

    return ''.join([transcription[nucleotide] for nucleotide in dna])
