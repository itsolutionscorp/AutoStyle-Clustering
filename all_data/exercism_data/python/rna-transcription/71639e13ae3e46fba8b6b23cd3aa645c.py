#-------------------------------------------------------------------------------
# Name:        mcface3000
# Purpose:      rna transcription
#-------------------------------------------------------------------------------

def to_rna(DNA):
    left_DNA = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ""
    for word in DNA:
        rna += left_DNA[word]
    return rna
