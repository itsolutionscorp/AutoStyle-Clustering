def to_rna(from_dna):
    dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    return "".join([dna_to_rna.get(n) for n in from_dna])
