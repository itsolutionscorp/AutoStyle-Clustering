class DNA:
    def __init__(self, word):
        self.word = word

    def to_rna(self):
        rna = ''
        for letter in self.word:
            if letter == 'T':
                rna += 'U'
            else:
                rna += letter
        return rna
