def to_rna(dna):
    dna_conv = {'G': 'C',
                'C': 'G',
                'T': 'A',
                'A': 'U'}
    rna = ""
    for nucleotides in dna:
        rna += dna_conv[nucleotides]
    return rna
