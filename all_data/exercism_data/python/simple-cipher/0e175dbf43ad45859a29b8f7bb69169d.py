from random import choice


class Cipher(object):
    a = ord('a')

    def __init__(self, key=''):
        if not key:
            key = [chr(choice(xrange(ord('a'), ord('z') + 1))) for v in range(0, 101)]
        self.key = [ord(c) % self.a for c in key]

    def decode(self, text):
        return "".join(self.char_for(c, i) for i, c in enumerate(text))

    def encode(self, text):
        text = (c for c in text.lower() if 'a' <= c <= 'z')
        return "".join(self.code_for(c, i) for i, c in enumerate(text))

    def key_for(self, i):
        return self.key[i % len(self.key)]

    def code_for(self, c, i):
        a, v, z = ord('a'), ord(c), ord('z')
        key = self.key_for(i)
        if a <= v <= z - key:
            return chr(v + key)
        else:
            return chr(v - ((z - key + 1) % a))

    def char_for(self, c, i):
        a, v, z = ord('a'), ord(c), ord('z')
        key = self.key_for(i)
        if a + key <= v <= z:
            return chr(v - key)
        else:
            return chr(v + ((z - key + 1) % a))


class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__('d' * 128)
