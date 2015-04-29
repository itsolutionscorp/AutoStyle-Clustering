class DNA(object):
    """
    Translate a given DNA string to the transcribed RNA
    string corresponding to it.
    """
    def __init__(self, dna_string):
        self._dna_string = dna_string

    def to_rna(self):
        return self._dna_string.replace('T', 'U')
