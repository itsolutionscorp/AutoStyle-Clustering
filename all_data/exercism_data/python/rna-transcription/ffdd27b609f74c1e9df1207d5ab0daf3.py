class DNA:
    word = ""
    def to_rna(self):
        res = ""
        for char in self.word:
            if char == 'T':
                res += "U"
            else:
                res += char
        return res

    def __init__(self, word):
        self.word = word
