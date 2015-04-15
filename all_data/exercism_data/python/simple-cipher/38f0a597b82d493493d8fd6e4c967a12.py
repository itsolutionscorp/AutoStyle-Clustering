from string import ascii_lowercase

az = ascii_lowercase


class Caesar:

    def __init__(self, key=None):
        if key:
            self.key = key
        else:
            self.key = 'd'

    def encode(self, inp, cipher_dir=1):
        out = ''
        pos = 0
        for enum, letter in enumerate(inp.lower()):
            if letter in az:
                char_key = self.key[pos % len(self.key)]
                out += az[(az.index(letter)
                           + cipher_dir * az.index(char_key)) % 26]
                pos += 1
        return out

    def decode(self, inp):
        return self.encode(inp, -1)


class Cipher(Caesar):

    def __init__(self, key=None):
        super(Cipher, self).__init__(key)


if __name__ == '__main__':
    c = Caesar()
    print(c.encode('abcd'))
    print(c.encode('1, 2, 3, Go!'))
    print(c.decode('defg'))
    cp = Cipher('d')
    print(cp.decode(cp.encode('abcd')))
    print(cp.encode('1, 2, 3, Go!'))
    print(cp.decode('defg'))
    key = 'duxrceqyaimciuucnelkeoxjhdyduucpmrxmaivacmybmsdrzwqxvbxsygzsabdjmdjabeorttiwinfrpmpogvabiofqexnohrqu'
    plaintext = 'adaywithoutlaughterisadaywasted'
    c = Cipher(key)
    c.encode(plaintext)
