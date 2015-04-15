import string


def decode(code):
    transtab = string.maketrans(string.lowercase, string.lowercase[::-1])
    return code.lower().translate(transtab, ' ')


def encode(code):
    cipher = 'abcdefghijklmnopqrstuvwxyz'
    transtab = string.maketrans(string.lowercase[::-1], string.lowercase)
    encoded = code.lower().translate(transtab, ' '.join((string.whitespace, string.punctuation)))
    return ' '.join(encoded[i:i+5] for i in range(0, len(encoded), 5))
