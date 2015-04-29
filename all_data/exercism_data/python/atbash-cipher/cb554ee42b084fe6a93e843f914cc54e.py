import string

trans = string.maketrans(string.ascii_lowercase,
                         "".join(reversed(string.ascii_lowercase)))


def encode(phrase):
    phrase = phrase.lower().translate(
        trans, string.punctuation + string.whitespace)
    return " ".join([phrase[i:i+5] for i in xrange(0, len(phrase), 5)])


def decode(cipher):
    return cipher.translate(trans, string.whitespace)
