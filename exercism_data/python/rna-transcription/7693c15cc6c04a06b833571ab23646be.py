def to_rna(dna_sequence):
    rna_sequence = []
    for strand in dna_sequence:
        if strand == 'C':
            rna_sequence.append('G')
        elif strand == 'G':
            rna_sequence.append('C')
        elif strand == 'A':
            rna_sequence.append('U')
        elif strand == 'T':
            rna_sequence.append('A')
    return ''.join(rna_sequence)
