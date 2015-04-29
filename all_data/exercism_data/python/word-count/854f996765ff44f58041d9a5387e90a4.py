class Phrase(object):
    def __init__(self, frase):
        self.frase = frase.lower()
        for char in self.frase:
            if char != ' ' and not char.isalnum():
                # Es un símbolo y no una letra
                self.frase = self.frase.replace(char, '')
    def word_count(self):
        split = self.frase.split()
        return dict([(word, split.count(word)) for word in split])
