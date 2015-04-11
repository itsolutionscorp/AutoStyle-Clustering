# script for Exercism project rna_transcription

def to_rna(dna):
    result = ""
    for c in dna:
        result += translate(c)
    return result

def translate(x):
    return {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U',
    }[x]
