from random import SystemRandom

class Cipher:
    ALPHABETS = 'abcdefghijklmnopqrstuvwxyz'

    def __init__(self, key=None):
        def random_key(length=100):
            rand = SystemRandom()
            return ''.join([rand.choice(self.ALPHABETS) for i in range(length)])
        if key is None:
            self.key = random_key()
        else:
            self.key = key

    def encode(self, input):
        def encode_ch(value, key):
            return chr((ord(value) + ord(key) - ord('a') - ord('a')) % 26 + ord('a'))
        return self.coding(input, encode_ch)

    def decode(self, input):
        def decode_ch(value, key):
            return chr((ord(value) - ord(key) + 26) % 26 + ord('a'))
        return self.coding(input, decode_ch)

    def coding(self, input, operation):
        def lower_alphabets(string):
            return ''.join([x.lower() for x in string if x.isalpha()])
        def aux(input):
            if len(input) == 0:
                return ''
            partial_result = ''.join([operation(x, y) for x, y in zip(input, self.key)])
            return partial_result + aux(input[len(self.key):])
        input = lower_alphabets(input)
        return aux(input)

class Caesar(Cipher):
    def __init__(self):
        super().__init__('dddddddddddddddd')
