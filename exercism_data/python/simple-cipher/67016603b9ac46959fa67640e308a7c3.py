import string
import random
class Caesar():
    
    def encode(self, s, mode="encode"):
        s_p = 1 if mode == "encode" else -1
        return s.lower().translate(str.maketrans(
            string.ascii_lowercase,
            string.ascii_lowercase[s_p * 3:] + string.ascii_lowercase[:s_p * 3],
            string.punctuation.join(string.whitespace).join(string.digits)))

    def decode(self, s, mode="decode"):
        return self.encode(s, mode)

class Cipher():
    def __init__(self, key=""):
        if key == "":
            for i in range(100):
                key += string.ascii_lowercase[random.randint(0,25)]
        self.key = key
        
    def encode(self, s, mode="encode"):
        position = 0
        result = ""
        shift_polarity = 1 if mode == "encode" else -1
        for i in s:
            shift = shift_polarity * string.ascii_lowercase.index(
                self.key[position % len(self.key)])
            old = string.ascii_lowercase.index(i)
            result = result + string.ascii_lowercase[(old + shift) % 26]
            position += 1
        return result

    def decode(self, s, mode="decode"):
        return self.encode(s, mode)
