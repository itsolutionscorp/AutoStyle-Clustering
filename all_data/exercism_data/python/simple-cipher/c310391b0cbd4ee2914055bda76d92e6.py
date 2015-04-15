from random import randint

class Cipher:
    def __init__(self, key = None):
        if key == None:
            #self.key = ''
            #for i in range(100):
            #    self.key += chr(randint(0, 26) + ord('a'))
            self.key = ''.join([chr(randint(0, 26) + ord('a')) for i in range(100)])
        elif not all(ord(k) in range(ord('a'), ord('z')+1) for k in key):
            raise ValueError('Key should only have lower case characters.')
        else:
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

