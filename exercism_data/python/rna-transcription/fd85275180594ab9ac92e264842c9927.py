class DNA(object):
    reemplazar = dict(zip(list('GCTA'), list('CGAU')))
    def __init__(self, dna):
        self.dna = dna

    def to_rna(self):
        ret = []
        for char in self.dna:
            ret.append(self.reemplazar[char])
        return ''.join(ret)
