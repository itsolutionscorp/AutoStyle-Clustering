import string


class DNA(object):
    """Represent a strand of DNA."""

    DNA_RNA_TRANSLATION_TABLE = string.maketrans('GCTA', 'CGAU')

    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        """Return the RNA complement of the DNA strand."""
        return self.dna_string.translate(self.DNA_RNA_TRANSLATION_TABLE)
