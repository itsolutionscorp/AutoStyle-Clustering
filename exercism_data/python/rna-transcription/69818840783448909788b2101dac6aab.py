import string

class DNA:
    """Represents DNA."""

    def __init__(self, dna):
        """Constructs new DNA object.

        Keyword arguments:
        dna -- DNA string.
        """
        self.dna = dna

    def to_rna(self):
        """Returns corresponding transcribed RNA string."""

        translate_table = string.maketrans('GCTA', 'CGAU')
        return self.dna.translate(translate_table)
