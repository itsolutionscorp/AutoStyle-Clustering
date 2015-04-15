# RNA Transcription exercism, 3rd iteration
import string

def to_rna(dna):
    rna_conversion = string.maketrans('GCTA','CGAU')
    return dna.translate(rna_conversion)
