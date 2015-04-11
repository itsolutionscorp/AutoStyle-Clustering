class DNA:

    def __init__(self, value):
        self.sequence = value

    def to_rna(self):
        return self.sequence.replace('T', 'U')
