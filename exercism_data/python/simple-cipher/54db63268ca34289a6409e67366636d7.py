from string import ascii_lowercase
from random import choice


class Cipher:
    """ Encode or decode a message based on a substitution cipher  key
    """
    def __init__(self, key=None):
        """
        :param key: String containing the key to be used for encoding/decoding
        """
        if key:
            self.key = _remove_non_alpha(key)
        else:
            self.key = ''.join([choice(ascii_lowercase) for i in range(100)])

    def _translate(self, msg, shift_char):
        """
        :param msg: String containing message to translate (either encode or decode)
        :param shift_char: Function to perform the transformation
        :return: String containing the translated version of the message
        """

        # Adjust the length of the key to the size of the message
        normalized_key = self.key * (len(msg) // len(self.key) + 1)

        return ''.join(shift_char(char, key) for char, key in zip(msg, normalized_key))

    def encode(self, msg):
        """
        :param msg: String containing message to be encoded
        :return: String containing an encoded version of the given message
        """

        # For every pair of chars from the message and key, shift the char by n characters,
        # where n is equal to the position of the key character within the alphabet
        shift_char = lambda char, key: chr(((ord(char) + ord(key) - 2 * ord('a'))
                                           % len(ascii_lowercase)) + ord('a'))
        return self._translate(_remove_non_alpha(msg), shift_char)

    def decode(self, msg):
        """
        :param msg: String containing message to be decoded
        :return: String containing a decoded version of the given message
        """

        # For every pair of chars from the message and key, shift the char back by n characters,
        # where n is equal to the position of the key character within the alphabet
        shift_char = lambda char, key: chr(((ord(char) - ord(key) + len(ascii_lowercase))
                                        % len(ascii_lowercase)) + ord('a'))
        
        return self._translate(msg, shift_char)


class Caesar(Cipher):
    def __init__(self):
        Cipher.__init__(self, 'd')


def _remove_non_alpha(str):
    """
    :param str: String to be normalized
    :return: A string that only contains alphanumeric characters, converted to lower case
    """
    return ''.join([c for c in str if c.isalpha()]).lower()
