ADENINE = 'A'
CYTOSINE = 'C'
GUANINE = 'G'
THYMINE = 'T'
URACIL = 'U'


class DNA(object):
    """
    Translate a given DNA string to the transcribed RNA
    string corresponding to it.
    """
    dna_nucleotides = {ADENINE, CYTOSINE, GUANINE, THYMINE}

    def __init__(self, dna_string):
        self._dna_string = dna_string
        if not self.is_valid():
            raise ValueError('Not Valid DNA Sequence')

    def is_valid(self):
        """
        Returns `True` if the DNA sequence contains valid DNA nucleotides.
        """
        for s in set(self._dna_string):
            if s not in self.dna_nucleotides:
                return False
        return True

    def to_rna(self):
        """
        Transcribes the DNA to an RNA sequence.
        """
        return self._dna_string.replace(THYMINE, URACIL)