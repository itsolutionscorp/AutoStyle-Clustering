import string

ALPHABET = "abcdefghijklmnopqrstuvwxyz"

def _chunks(l, n):
    """
    Yield successive n-sized chunks from l.
    """
    for i in xrange(0, len(l), n):
        yield l[i:i+n]

def _translate(c):
    return chr(ord('z') - ord(c) + ord('a'))

def encode(decoded):
    chars = []
    for c in decoded.lower():
        if c in string.punctuation or c.isspace():
            continue

        if c in ALPHABET:
            c = _translate(c)
        chars.append(c)
    return ' '.join(
        [
            ''.join(i)
            for i in list(_chunks(chars, 5))
        ]
    )

def decode(encoded):
    chars = []
    for c in encoded:
        if c.isspace():
            continue
        chars.append(_translate(c))
    return ''.join(chars)
