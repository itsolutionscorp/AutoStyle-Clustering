def to_rna(dna):
    x_table = {
         'G': 'C',
         'C': 'G',
         'T': 'A',
         'A': 'U'
    }

    return "".join([x_table[c] for c in dna])
