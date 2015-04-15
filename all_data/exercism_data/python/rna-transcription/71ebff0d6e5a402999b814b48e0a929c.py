class DNA(object):
    def __init__(self, sequence):
        self.sequence = sequence

    def to_rna(self):
        """ Translates a DNA sequence to RNA """
        key = str.maketrans("T", "U")
        return self.sequence.translate(key)
