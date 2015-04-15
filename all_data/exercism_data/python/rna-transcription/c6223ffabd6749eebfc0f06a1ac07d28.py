class DNA(object):
    def __init__(self, string=''):
        self.string = string

    def to_rna(self):
        mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
        translated = [mapping[x] for x in self.string]
        return ''.join(translated)
