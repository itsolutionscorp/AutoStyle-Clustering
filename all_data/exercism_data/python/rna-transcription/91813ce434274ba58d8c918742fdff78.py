class DNA(object):
    """A DNA sequence."""

    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        """Converts DNA sequence to RNA sequence."""

        return self.sequence.replace('T', 'U')
