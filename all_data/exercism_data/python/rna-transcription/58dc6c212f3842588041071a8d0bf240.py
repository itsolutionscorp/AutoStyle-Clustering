
mapping = {'G': 'C',
           'C': 'G',
           'T': 'A',
           'A': 'U'}

def to_rna(strand):
    return ''.join((mapping[letter] for letter in strand))
