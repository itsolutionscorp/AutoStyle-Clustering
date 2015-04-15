from itertools import cycle


class Cipher(object):

    def __init__(self, key='a'):
        self.key_sequence = [ord(x)-97 for x in key.lower()]

    def encode(self, to_encode):

        ret_val = [chr(ord(letter) + key)
                   for letter, key in zip(to_encode.lower(), cycle(self.key_sequence))
                   if ord(letter) > 96 < 123]

        return ''.join(Cipher.correct_chars(ret_val))

    def decode(self, to_decode):

        ret_val = [chr(ord(letter) - key)
                   for letter, key in zip(to_decode.lower(), cycle(self.key_sequence))
                   if ord(letter) > 96 < 123]

        return ''.join(Cipher.correct_chars(ret_val))

    @staticmethod
    def correct_chars(to_correct):
        # ensures that only a-z are used
        for index, letter in enumerate(to_correct):
            if ord(letter) < 97:
                to_correct[index] = chr(ord(letter) + 26)
            if ord(letter) > 122:
                to_correct[index] = chr(ord(letter) - 26)

        return to_correct


class Caesar(Cipher):

    def __init__(self):
        super(Caesar, self).__init__('d')
