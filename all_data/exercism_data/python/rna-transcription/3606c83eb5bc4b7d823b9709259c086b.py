class DNA:
    def __init__(self, strain):
        self.strain = strain

    def to_rna(self):
        return self.strain.replace('T', 'U')
