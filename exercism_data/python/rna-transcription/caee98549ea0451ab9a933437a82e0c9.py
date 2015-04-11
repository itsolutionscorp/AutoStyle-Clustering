"""Transcribe DNA to RNA"""

def to_rna(dna):
    rna = ""
    complement = {'C':'G', 'G':'C', 'T':'A','A':'U'}
    for char in dna:
        rna += complement[char]

    return rna
