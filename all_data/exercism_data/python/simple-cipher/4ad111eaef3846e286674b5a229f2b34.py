import string as s


class Cipher(object):
    """docstring for Cipher"""
    def __init__(self, keyString="a"):
        self.strip = s.whitespace + s.punctuation + s.digits

        shift_dict = {s.ascii_lowercase[i]: i
                      for i in range(len(s.ascii_lowercase))}
        self.offsets = [shift_dict[char] for char in keyString]

        self.alpha_lookup = s.ascii_lowercase * 2

    def encode(self, string):

        # strip unwanted chars and send to lowercase
        clean_string = self._clean_string(string)

        letter_pairs = self._get_offset_letter_pairs(clean_string)

        encoded_list = [self.alpha_lookup[self.alpha_lookup.index(char) + offset]
                        for char, offset in letter_pairs]
        return ''.join(encoded_list)

    def decode(self, string):

        letter_pairs = self._get_offset_letter_pairs(string)

        decoded_list = [self.alpha_lookup[self.alpha_lookup.index(char) - offset]
                        for char, offset in letter_pairs]
        return ''.join(decoded_list)

    def _get_offset_letter_pairs(self, string):

        # Add 0 offsets if the keystring wasn't long enough
        if len(string) > len(self.offsets):
            self.offsets += [0] * (len(string) - len(self.offsets))

        # Associate letters of string with their offsets
        letter_pairs = zip(string, self.offsets)

        return letter_pairs

    def _clean_string(self, string):
        translation_table = string.maketrans(s.ascii_uppercase, s.ascii_lowercase, self.strip)
        clean_string = string.translate(translation_table)
        return clean_string


class Caesar(object):

    def __init__(self):
        self.strip = s.whitespace + s.punctuation + s.digits
        self.alpha = s.ascii_lowercase
        self.shifted = self.alpha[3:] + self.alpha[:3]

    def encode(self, string):
        # send to lowercase:
        string = string.lower()

        translation_table = string.maketrans(self.alpha, self.shifted, self.strip)
        return string.translate(translation_table)

    def decode(self, string):
        translation_table = string.maketrans(self.shifted, self.alpha, self.strip)
        return string.translate(translation_table)
