import string

class DNA():

    def __init__(self, *args):
        self.data = args[0]

    """Transcribes a DNA string to RNA by replacing T with U"""
    def to_rna(self):
        return string.replace((self.data), "T", "U")
