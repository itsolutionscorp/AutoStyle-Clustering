from string import maketrans


class AtBash:
    
    def __init__(self):
        plain =  "abcdefghijklmnopqrstuvwxyz"
        cipher = "zyxwvutsrqponmlkjihgfedcba"
        self.translation = maketrans(plain, cipher)

    def decode(self, line):
        translated = line.lower().translate(self.translation)
        return ''.join(translated.split()) #  clean whitespace

    def encode(self, line):
        translated = line.lower().translate(self.translation)
        translated = [ x for x in translated if x.isalpha() or x.isdigit()]
        res = ""
        for i in range(len(translated)): 
            if not i % 5 and i:
                res += ' '
            res += translated[i]
        return res
            

def decode(line):
    a = AtBash()
    return a.decode(line)

def encode(line):
    a = AtBash()
    return a.encode(line)
