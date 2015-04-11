class DNA:
    def __init__(self, sequence):
        """
        Initializes this DNA with sequence.
        """
        self.sequence = sequence

    def to_rna(self):
        """
        Transcribe this DNA strand to RNA by replacing all occurences of of thymidine with uracil
        """
        return self.sequence.replace('T', 'U')
