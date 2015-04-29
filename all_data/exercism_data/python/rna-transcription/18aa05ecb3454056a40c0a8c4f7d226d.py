def to_rna(dna_sequence):
    rna_complement = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U'
    }

    rna_sequence = list()
    for dna_nucleotide in dna_sequence:
        rna_sequence.append(rna_complement[dna_nucleotide])

    return ''.join(rna_sequence)
