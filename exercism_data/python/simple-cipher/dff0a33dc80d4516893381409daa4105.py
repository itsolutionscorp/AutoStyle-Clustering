import string
import random
class Caesar():

    def __init__(self):
        pass
    
    def encode(self, s):
        return s.lower().translate(str.maketrans(
            string.ascii_lowercase,
            string.ascii_lowercase[3:]+string.ascii_lowercase[0:3],
            string.punctuation.join(string.whitespace).join(string.digits)))

    def decode(self, s):
        return s.lower().translate(str.maketrans(
            string.ascii_lowercase,
            string.ascii_lowercase[-3:]+string.ascii_lowercase[:-3],
            string.punctuation.join(string.whitespace).join(string.digits)))

class Cipher():
    def __init__(self, key='.'):
        if key == '.':
            key = ""
            for i in range(100):
                key += string.ascii_lowercase[random.randint(0,25)]
        self.key = key
        
    def encode(self, s):
        position = 0
        result = ""
        for i in s:
            shift = string.ascii_lowercase.index(self.key[position%len(self.key)])
            old = string.ascii_lowercase.index(i)
            result = result + string.ascii_lowercase[(old+shift)%26]
            position+=1
        return result

    def decode(self, s):
        position = 0
        result = ""
        for i in s:
            shift = string.ascii_lowercase.index(self.key[position%len(self.key)])
            old = string.ascii_lowercase.index(i)
            result = result + string.ascii_lowercase[(old-shift)%26]
            position+=1
        return result
