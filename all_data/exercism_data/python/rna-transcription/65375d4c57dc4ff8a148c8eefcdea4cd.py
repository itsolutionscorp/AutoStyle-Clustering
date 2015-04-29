from string import maketrans


class DNA(object):
    """Represent a strand of DNA."""

    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self, TRANSLATION_TABLE=maketrans('GCTA', 'CGAU')):
        """Return the RNA complement of the DNA strand."""
        return self.dna_string.translate(TRANSLATION_TABLE)
