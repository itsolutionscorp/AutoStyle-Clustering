def to_rna(dna):
    rna_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    return ''.join(rna_dict[x] for x in dna)
