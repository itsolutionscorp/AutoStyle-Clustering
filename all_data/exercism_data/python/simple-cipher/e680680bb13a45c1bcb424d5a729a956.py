from string import maketrans, ascii_letters
from random import choice

class Caesar:
    def __init__(self):
        self.encoder = maketrans('abcdefghijklmnopqrstuvwxyz', 'defghijklmnopqrstuvwxyzabc')
        self.decoder = maketrans('defghijklmnopqrstuvwxyzabc', 'abcdefghijklmnopqrstuvwxyz')

    def cleanse(self, txt):
        return ''.join([s for s in txt.lower() if s.isalpha()])

    def encode(self, msg):
        return self.cleanse(msg).translate(self.encoder)

    def decode(self, msg):
        return self.cleanse(msg).translate(self.decoder)


class Cipher:
    def __init__(self, key=None):
        if not key:
            self.key = self.generate_key()
        else:
            self.key = key

        self.check_key()

    def generate_key(self):
        return ''.join([choice(ascii_letters) for i in range(139)]).lower()

    def check_key(self):
        if self.key != ''.join([s for s in self.key.lower() if s.isalpha()]):
            raise Exception("Bad Key")

    def repeat_to_length(self, string_to_expand, length):
        return (string_to_expand * ((length/len(string_to_expand))+1))[:length]

    def encode(self, msg):
        self.mesg = msg
        if len(msg) != len(self.key):
            self.key = self.repeat_to_length(self.key, len(msg))
        return ''.join([chr(self.wrap_ascii(sum(x))) for x in zip([ord(a)-97 for a in self.key], [ord(a) for a in msg])])

    def decode(self, msg):
        return ''.join([chr(self.wrap_ascii(sum(x))) for x in zip([0-(ord(a)-97) for a in self.key], [ord(a) for a in msg])])

    def wrap_ascii(self, num):
        if num > 122:
            return (num % 122) + 96
        elif num < 97:
            return num + 26
        else:
            return num
