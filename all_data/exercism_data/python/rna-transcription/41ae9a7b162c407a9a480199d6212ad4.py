transcription = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }

def to_rna(strand):
    rna_strand = ""
    for nucleotide in strand:
        rna_strand += transcription[nucleotide]
    return rna_strand
