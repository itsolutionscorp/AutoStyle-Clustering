class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence.upper().strip()

    def to_rna(self):
        if not self.sequence: return ''

        return self.sequence.replace('T', 'U')
