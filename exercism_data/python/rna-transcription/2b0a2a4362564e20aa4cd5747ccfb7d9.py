def to_rna(nucleotides):
    complement = ""
    for nucleotide in nucleotides:
        if nucleotide == 'C':
            complement += 'G'
        elif nucleotide == 'G':
            complement += 'C'
        elif nucleotide == 'T':
            complement += 'A'
        elif nucleotide == 'A':
            complement += 'U'
    return complement
