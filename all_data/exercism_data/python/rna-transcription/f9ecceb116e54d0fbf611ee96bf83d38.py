def to_rna(dna):
    mapping = {'G': 'C',
               'C': 'G',
               'T': 'A',
               'A': 'U'}

    rna = ""
    for index in range(len(dna)):
        dna_nucleotide = dna[index]
        rna += mapping[dna_nucleotide]
    return rna
