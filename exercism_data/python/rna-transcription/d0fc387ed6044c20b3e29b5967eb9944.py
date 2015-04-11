transcription = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }

def to_rna(strand):
    result = ""
    for nucleotide in strand:
        result += transcription[nucleotide]
    return result
