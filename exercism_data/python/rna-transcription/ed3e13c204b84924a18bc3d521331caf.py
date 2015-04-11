def to_rna(dna_sequence):
    rna_sequence = ''
    for base in dna_sequence:
        if base == 'G':
            rna_sequence += 'C'
        if base == 'C':
            rna_sequence += 'G'
        if base == 'T':
            rna_sequence += 'A'
        if base == 'A':
            rna_sequence += 'U'
    return rna_sequence
