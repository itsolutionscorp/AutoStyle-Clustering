"""
Rna Transcription
"""

def to_rna(dna_strand):
    result = []
    complements = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}   
    
    for nucleotide in dna_strand:
        result.append(complements[nucleotide])
        
    return "".join(result)
