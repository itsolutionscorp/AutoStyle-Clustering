class DNA(object):

    """Class that represent a DNA sequence."""

    def __init__(self, nucleotides):
        self._nucleotides = nucleotides

    def to_rna(self):
        """Return a RNA sequence."""
        return self._nucleotides.replace("T", "U")
