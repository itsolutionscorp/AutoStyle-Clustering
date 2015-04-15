import re

class Caesar:
    def __init__(self, key = 'd'):
        if (len(key) > 1 and key[0] != key[1]):
            self.key = key
        else:
            self.key = key[0]

    def encode(self, input):
        cleaned = self.__clean(input)
        return "".join([self.__encode_letter(letter, i) for i, letter in enumerate(cleaned)])

    def decode(self, input):
        return "".join([self.__decode_letter(letter, i) for i, letter in enumerate(input)])

    def __clean(self, input):
        return re.sub(r"[^\w]|\d", "", input.lower())

    def __encode_letter(self, letter, i):
        code = self.__use_key_on_letter(letter, i, -1)
        code = self.__correct_rotation(code, -1)
        return chr(code)

    def __decode_letter(self, letter, i):
        code = self.__use_key_on_letter(letter, i, 1)
        code = self.__correct_rotation(code, 1)
        return chr(code)

    def __use_key_on_letter(self, letter, i, direction):
        if (len(self.key) == 1):
            code = ord(letter) + ord(self.key[0]) * -direction
        else:
            code = ord(letter) + ord(self.key[i]) * -direction
        return code + (97 * direction)

    def __correct_rotation(self, code, direction):
        if not (self.__is_alphanumeric(chr(code))):
            code += 26 * direction
        return code

    def __is_alphanumeric(self, letter):
        return re.match(r"[abcdefghijklmnopqrstuvwxyz]", letter);

def Cipher(key = 'd'):
    return Caesar(key)
