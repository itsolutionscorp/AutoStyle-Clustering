class DNA(object):
    """
    Represent a sequence of DNA and support transcription to RNA.
    """
    def __init__(self, nucleotide_sequence):
        self.nucleotide_sequence = nucleotide_sequence

    def to_rna(self):
        return self.nucleotide_sequence.replace("T", "U")
