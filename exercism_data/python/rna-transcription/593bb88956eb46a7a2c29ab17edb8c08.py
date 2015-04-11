import string

trans_dict = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(dna):
    return string.join(map(lambda x: trans_dict[x], dna.upper()), '')
