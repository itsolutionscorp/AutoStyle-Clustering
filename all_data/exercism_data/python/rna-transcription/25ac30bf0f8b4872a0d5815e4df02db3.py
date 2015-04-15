def to_rna(dna):
    nucleotides = list(dna)
    result = ''
    for nucleotide in nucleotides:
        if nucleotide == 'G':
            result += 'C'
        elif nucleotide == 'C':
            result += 'G'
        elif nucleotide == 'T':
            result += 'A'
        elif nucleotide == 'A':
            result += 'U'
    return result
