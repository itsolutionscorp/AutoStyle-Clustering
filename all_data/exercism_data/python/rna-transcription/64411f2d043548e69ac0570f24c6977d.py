from operator import add

class DNA(object):
    def __init__(self, dna):
        self.dna = dna 
        """ Following the README this has to be:
        self.translate = {'A': 'U',
                          'T': 'A',
                          'G': 'C',
                          'C': 'G'}
        but the tests want:"""
        self.translate = {'A': 'A',
                          'T': 'U',
                          'G': 'G',
                          'C': 'C'}

    def to_rna(self):
        return reduce(add, map(lambda x: self.translate[x], list(self.dna)))
