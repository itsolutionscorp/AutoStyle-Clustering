from string import punctuation, whitespace, ascii_lowercase, maketrans

CIPHER = maketrans(ascii_lowercase, ascii_lowercase[::-1])


def _apply_cipher(text):
    return text.lower().translate(CIPHER, punctuation + whitespace)


def _blocks_of(text):
    return " ".join([text[i:i+5] for i in range(0, len(text), 5)])


def encode(plaintext):
    return _blocks_of(_apply_cipher(plaintext))


def decode(encoded):
    return _apply_cipher(encoded)
