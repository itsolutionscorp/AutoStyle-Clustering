def to_rna(dna):
    code_translation = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    rna = ""
    for n in dna:
        rna += code_translation[n]
    return rna
