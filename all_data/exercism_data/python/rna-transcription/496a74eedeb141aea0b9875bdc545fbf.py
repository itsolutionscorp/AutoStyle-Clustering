def to_rna(seq):
    dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return "".join([dna_to_rna[x] for x in seq])
