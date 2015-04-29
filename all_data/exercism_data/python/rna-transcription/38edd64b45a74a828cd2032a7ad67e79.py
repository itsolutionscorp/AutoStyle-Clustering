def to_rna(nucleotides):
    return ''.join(map(complement, nucleotides))

def complement(nucleotide):
    if nucleotide == 'G':
        return 'C'
    elif nucleotide == 'C':
        return 'G'
    elif nucleotide == 'T':
        return 'A'
    elif nucleotide == 'A':
        return 'U'
    else:
        return nucleotide
