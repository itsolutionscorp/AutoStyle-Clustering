def transcribed(nucleotide):
    if nucleotide == 'A':
        return 'U'
    elif nucleotide == 'C':
        return 'G'
    elif nucleotide == 'G':
        return 'C'
    elif nucleotide == 'T':
        return 'A'
    else:
        return None

def to_rna(dna_strand):
    rna_sequence = ''
    for nucleotide in dna_strand:
        rna_sequence += transcribed(nucleotide)
    
    return rna_sequence
            
