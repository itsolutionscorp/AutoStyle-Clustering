import string


reverse_ascii = ''.join(reversed(string.ascii_lowercase))


def split_into_chunks(l, n):
    n = max(1, n)
    return [l[i:i + n] for i in range(0, len(l), n)]


def encode(plaintext):
    trans = str.maketrans(string.ascii_lowercase, reverse_ascii)

    allowed = string.ascii_lowercase + string.digits

    plaintext = ''.join(c for c in plaintext.lower() if c in allowed)

    characters = plaintext.translate(trans)

    chunks = split_into_chunks(characters, 5)

    return ' '.join([''.join(chunk) for chunk in chunks])


def decode(ciphertext):
    trans = str.maketrans(reverse_ascii, string.ascii_lowercase)

    allowed = string.ascii_lowercase + string.digits

    ciphertext = ''.join(c for c in ciphertext.lower() if c in allowed)

    return ciphertext.translate(trans)
