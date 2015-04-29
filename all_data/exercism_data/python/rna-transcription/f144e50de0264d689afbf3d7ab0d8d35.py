import string

trans_dict = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def char_to_rna(c):
    if c in trans_dict:
        return trans_dict[c]
    raise TypeError("Character %s is not part of the dna alphabet." % c)

def to_rna(dna):
    return string.join(map(char_to_rna, dna.upper()), '')
