def to_rna(dna):
    dna_dict = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    rna = []
    for n in dna:
        rna.append(dna_dict[n])

    return ''.join(rna)
