class DNA(object):
    """A DNA sequence.

    Consists of nucleotides 'A', 'T', 'G' and 'C'.
    """

    def __init__(self, sequence):
        """
        @param sequence: sequence of 'A', 'T', 'G' and 'C'.
        @type sequence: string
        """

        self._sequence = sequence

    def to_rna(self):
        """Converts DNA sequence to RNA sequence.

        An RNA sequence consists of the same nucleotides as DNA,
        except with 'T's in the place of 'U's.

        @return: string representing RNA sequence
        """

        return self._sequence.replace('T', 'U')
