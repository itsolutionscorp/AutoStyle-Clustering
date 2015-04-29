class DNA(object):
    RNA_MAP = {'T': 'U'}

    def __init__(self, chain):
        self.chain = chain

    def to_rna(self):
        return ''.join(self.RNA_MAP.get(char, char) for char in self.chain)
