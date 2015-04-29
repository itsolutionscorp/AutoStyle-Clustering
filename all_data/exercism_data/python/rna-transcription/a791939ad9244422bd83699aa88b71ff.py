translate = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(dna):
    return ''.join([translate[n] for n in dna])
