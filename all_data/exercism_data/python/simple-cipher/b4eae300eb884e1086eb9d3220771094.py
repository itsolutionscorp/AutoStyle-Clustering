import string
from collections import OrderedDict
from random import choice


class Cipher(object):
    _delete_chars = string.punctuation + string.whitespace + string.digits

    def __init__(self, key=None):
        super(Cipher, self).__init__()
        if not key:
            key = ''.join(choice(string.ascii_lowercase) for i in xrange(0, 100))

        if not all(c.islower() and c.isalpha() for c in key):
            raise ValueError("Invalid key!")

        self.key = key

        #strip duplicate chars in key
        key = ''.join(OrderedDict.fromkeys(key).keys())

        clear = string.ascii_lowercase
        if len(key) == 1:
            # assume we are performing a shift cypher
            offset = ord(key[0]) - ord('a')
            cypher = clear[offset:] + clear[:offset]
        else:
            # assume we are performing a substitution cypher
            cypher = key + ''.join(c for c in string.ascii_lowercase if not c in key)

        self._encoder = string.maketrans(clear, cypher)
        self._decoder = string.maketrans(cypher, clear)

    def encode(self, text):
        return text.lower().translate(self._encoder, self._delete_chars)

    def decode(self, text):
        return text.lower().translate(self._decoder, self._delete_chars)


class Caesar(Cipher):
    def __init__(self):
        super(Caesar, self).__init__('d')
