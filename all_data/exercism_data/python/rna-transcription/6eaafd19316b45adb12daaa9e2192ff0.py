def to_rna(dna):
    map_dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ''
    for element in dna:
        rna = rna + map_dna_to_rna[element]

    return rna
