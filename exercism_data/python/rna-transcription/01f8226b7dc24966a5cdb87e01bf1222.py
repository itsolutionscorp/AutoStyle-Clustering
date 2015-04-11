import string

def char_to_rna(c):
    if c is 'G':
        return 'C'
    if c is 'C':
        return 'G'
    if c is 'T':
        return 'A'
    if c is 'A':
        return 'U'
    raise TypeError("Character %s is not part of the dna alphabet." % c)

def to_rna(dna):
    return string.join(map(char_to_rna, dna.upper()), '')
