import re


class DNA(object):
    """
    DNA holds a string sequence of nucleotides, provides to_rna()
    for transcription to RNA sequence.
    """

    def __init__(self, sequence):
        sequence = sequence.upper()
        if re.match('[^CGAT]', sequence):
            raise InvalidSequenceException("Bad sequence: " + sequence)
        self.sequence = sequence.upper()

        self.complementary_nucleotides = {
            "G": "C",
            "C": "G",
            "A": "U",
            "T": "A"
            }

    def to_rna(self):
        transcription = ''
        for nucleotide in self.sequence:
            transcription += self.complementary_nucleotides[nucleotide]
        return transcription


class InvalidSequenceException(Exception):
    pass
