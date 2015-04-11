class DNA:

    def __init__(self, text):
        self.text = text

    def to_rna(self):
        return self.text.replace('T', 'U')
