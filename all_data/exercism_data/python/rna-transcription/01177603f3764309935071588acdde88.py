RULES = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}


def to_rna(string):
    return ''.join(map( lambda x: RULES[x], string))
