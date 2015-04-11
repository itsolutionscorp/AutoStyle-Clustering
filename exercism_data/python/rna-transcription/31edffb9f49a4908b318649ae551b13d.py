import string
class DNA:

    def __init__(self, word):
       self.word = word

    def to_rna(self):
        dna = 'GCTA'
        out = 'CGAU'
        trans = self.word.maketrans(dna, out)
        return self.word.translate(trans)
