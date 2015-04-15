import string

class DNA():

    def __init__(self, data):
        self.data = data

    """Transcribes a DNA string to RNA by replacing T with U"""
    def to_rna(self):
        return self.data.replace("T", "U")
