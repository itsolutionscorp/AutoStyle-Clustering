class DNA:
    def __init__(self, strand):
        self.__strand = strand

    def to_rna(self):
        return self.__strand.replace('T', 'U')
