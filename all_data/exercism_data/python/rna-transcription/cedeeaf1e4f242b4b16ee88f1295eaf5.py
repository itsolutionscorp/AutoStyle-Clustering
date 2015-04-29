def DNA(sequence):
    return dna(sequence)


class dna(object):
    """a dna"""

    _sequence = ""

    def __init__(self, sequence):
        self._sequence = sequence

    def to_rna(self):
        return self._sequence.replace('T','U')
