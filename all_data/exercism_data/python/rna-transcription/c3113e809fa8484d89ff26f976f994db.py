""" dna """


def to_rna(dna):
    """ to_rna function """
    trans = {'G': 'C',
             'C': 'G',
             'T': 'A',
             'A': 'U'}
    return "".join([trans[i] for i in dna])
