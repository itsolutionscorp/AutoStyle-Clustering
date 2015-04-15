import string

DNA_RNA_TRANSLATION_TABLE = string.maketrans('GCTA', 'CGAU')


class DNA(object):
    """Represent a strand of DNA."""

    def __init__(self, dna_string):
        self.dna_string = dna_string

    def to_rna(self):
        """Return the RNA complement of the DNA strand."""
        return string.translate(self.dna_string, DNA_RNA_TRANSLATION_TABLE)
