def to_rna(dna_sequence):
    result = []
    for each_nucleotide in dna_sequence:
        if each_nucleotide == 'G':
            result.append('C')
        elif each_nucleotide == 'C':
            result.append('G')
        elif each_nucleotide == 'T':
            result.append('A')
        elif each_nucleotide == 'A':
            result.append('U')
        else:
            return 'Mutant'
    return ''.join(result)
