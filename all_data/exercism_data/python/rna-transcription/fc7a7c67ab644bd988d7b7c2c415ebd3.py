#
# Submission file for the Python "rna-transcription" exercise.
#

transcript = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}


def to_rna(dna):
    rna = ""
    for nucleotide in dna:
        rna += transcript[nucleotide]
    return rna
