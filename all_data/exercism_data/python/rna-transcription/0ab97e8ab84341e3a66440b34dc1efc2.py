class DNA(object):

    """Class that represent a DNA sequence."""

    def __init__(self, nucleotids):
        self._nucleotids = nucleotids

    def to_rna(self):
        """Return a RNA sequence."""
        return self._nucleotids.replace("T", "U")
