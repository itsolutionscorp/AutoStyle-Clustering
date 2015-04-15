class DNA(object):
    """A DNA sequence."""

    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        """Converts DNA sequence to RNA sequence."""

        # Replace each 'U' by 'T'.
        rna_sequence = ''.join('U' if x=='T' else x for x in self.sequence)

        return rna_sequence
