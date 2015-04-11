def decode(cipher):
    return ''.join(c for c in codec(cipher))


def encode(plain):
    cipher = [c for c in codec(plain)]
    blocks = (''.join(cipher[i:i + 5]) for i in xrange(0, len(cipher), 5))
    return ' '.join(blocks)


def codec(text):
    key = [chr(v) for v in xrange(ord('a'), ord('z') + 1)][::-1]

    for c in text.lower():
        if c.isalpha():
            yield key[ord(c) - ord('a')]
        elif c.isalnum():
            yield c
