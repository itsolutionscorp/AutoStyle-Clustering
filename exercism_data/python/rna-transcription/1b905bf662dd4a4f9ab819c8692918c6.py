class DNA(object):
    """A DNA sequence."""

    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        """Converts DNA sequence to RNA sequence."""

        rna_sequence = self.sequence.replace('T', 'U')

        return rna_sequence
