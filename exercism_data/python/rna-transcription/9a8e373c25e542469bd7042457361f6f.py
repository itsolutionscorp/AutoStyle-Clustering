class DNA(object):
    def __init__(self, iput):
        self.iput = iput
        self.transtable = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
        return
    def to_rna(self):
        oput = ''
        for i in self.iput:
            oput += self.transtable[i]
        return oput
