class Cipher:
    def __init__(self, key = 'a'):
        self.key = key

    def encode(self, word):
        return self.translate(word, 1)

    def decode(self, word):
        return self.translate(word, -1)

    def translate(self, word, direction):
        out = []
        for i in range(len(word)):
            if ord('a') <= ord(word[i].lower()) <= ord('z'):
                diff = ord(self.key[i % len(self.key)]) - ord('a')
                base = ord(word[i].lower()) - ord('a')
                new = (base + direction * diff) % 26
                out.append(chr(ord('a') + new))
        return ''.join(out)
        
class Caesar(Cipher):
    def __init__(self):
        self.key = 'd'

