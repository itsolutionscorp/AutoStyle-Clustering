import string

def to_rna(strand):
    conv = ''
    for nucleotide in strand: conv = conv + translate(nucleotide)
    return conv

def translate(nucleotide):
    if nucleotide == 'G': return 'C'
    elif nucleotide == 'C': return 'G'
    elif nucleotide == 'T': return 'A'
    elif nucleotide == 'A': return 'U'
    else: return
