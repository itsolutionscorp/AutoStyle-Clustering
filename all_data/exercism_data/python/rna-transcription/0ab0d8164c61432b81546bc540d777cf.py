dna_to_rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
rna_to_dna = {r:d for d, r in dna_to_rna.items()}

def to_rna(dna):
    return ''.join([dna_to_rna[n] for n in dna])
