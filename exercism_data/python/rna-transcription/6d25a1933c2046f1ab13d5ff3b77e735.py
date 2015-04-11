def to_rna(dna_string):
    rna_string = ''

    for nucleotide in dna_string:
        if nucleotide == 'G':
            rna_string += 'C'
        elif nucleotide == 'C':
            rna_string += 'G'
        elif nucleotide == 'T':
            rna_string += 'A'
        elif nucleotide == 'A':
            rna_string += 'U'

    return rna_string
