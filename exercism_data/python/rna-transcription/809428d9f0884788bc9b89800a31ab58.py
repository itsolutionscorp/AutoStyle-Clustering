class DNA(object):
    def __init__(self, iput):
        self.iput = iput
        self.transtable = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
        return
    def to_rna(self):
        oput = list(self.iput)
        for i in range(0, len(oput)):
            if oput[i] in self.transtable:
                oput[i] = self.transtable[oput[i]]
        return ''.join(c for c in oput)
