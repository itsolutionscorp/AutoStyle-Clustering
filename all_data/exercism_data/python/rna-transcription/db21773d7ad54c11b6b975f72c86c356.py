mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

def to_rna(dna):
    return "".join([mapping[nucleo] for nucleo in dna])
