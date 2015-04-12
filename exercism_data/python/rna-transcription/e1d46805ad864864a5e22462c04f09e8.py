def to_rna(dna):
    rna_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ""

    for c in dna:
        if c in rna_dict:
            rna += rna_dict[c]

    return rna