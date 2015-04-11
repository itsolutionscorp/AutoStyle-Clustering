class DNA:

    def __init__(self, value):
        self.sequence = value

    def to_rna(self):
        if self.sequence:
            return self.sequence.replace('T', 'U')
        return None
