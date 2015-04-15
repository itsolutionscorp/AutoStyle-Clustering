A = ord('a')
Z = ord('z')

class Caesar:

    @staticmethod

    def encode(st):
        return Cipher().translate(st)

    @staticmethod

    def decode(st):
        return Cipher().translate(st, True)

class Cipher:
    def __init__(self, k='d'):
        self.offset = [ord(c) - A for c in k]

    def encode(self, st):
        return self.translate(st)

    def decode(self, st):
        return self.translate(st, True)

    def translate(self, st, invert = False):
        result = ''
        olen = len(self.offset)

        for i,c in enumerate(st.lower()):
            if not c.isalpha():
                continue

            if invert:
                n = ord(c) - (self.offset[i % olen])
            else:
                n = ord(c) + (self.offset[i % olen])

            if n > Z:
                n -= 26
            elif n < A:
                n += 26
            result += chr(n)

        return result
