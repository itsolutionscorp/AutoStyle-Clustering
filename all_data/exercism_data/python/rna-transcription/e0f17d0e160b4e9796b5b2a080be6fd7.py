import re

def to_rna(dna):
    # error handling
    assert re.search(r'^[ACGT]*$',dna), "Improper DNA sequence. Must be formed with nucleotides A, C, G, or T."

    # declare RNA to DNA transcription legend
    transcription = {'A':'U',
                     'C':'G',
                     'G':'C',
                     'T':'A'}

    # transcribe DNA and return result
    rna = ""
    for char in dna:
        rna += transcription[char.upper()]

    return rna
