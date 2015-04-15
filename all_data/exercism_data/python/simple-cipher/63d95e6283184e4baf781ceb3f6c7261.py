class Caesar:

    @staticmethod
    def encode(to_encode):
        ret_val = [chr(ord(letter) + 3)
                   for letter in to_encode.lower()
                   if ord(letter) > 96 < 123]

        return ''.join(Cipher.correct_chars(ret_val))

    @staticmethod
    def decode(to_decode):
        ret_val = [chr(ord(letter) - 3)
                   for letter in to_decode
                   if ord(letter) > 96 < 123]

        return ''.join(Cipher.correct_chars(ret_val))


class Cipher:

    def __init__(self, key='a'):
        self.key_sequence = [ord(x)-97 for x in key.lower()]

    def encode(self, to_encode):
        # make key sequence long enough
        while len(self.key_sequence) < len(to_encode):
            self.key_sequence *= 2

        ret_val = [chr(ord(letter) + self.key_sequence[index])
                   for index, letter in enumerate(to_encode)]

        return ''.join(Cipher.correct_chars(ret_val))

    def decode(self, to_decode):
        # make key sequence long enough
        while len(self.key_sequence) < len(to_decode):
            self.key_sequence *= 2

        ret_val = [chr(ord(letter) - self.key_sequence[index])
                   for index, letter in enumerate(to_decode)]

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
