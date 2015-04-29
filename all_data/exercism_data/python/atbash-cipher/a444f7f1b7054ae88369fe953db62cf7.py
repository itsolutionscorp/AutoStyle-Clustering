from string import punctuation, whitespace, ascii_lowercase, maketrans

"""Create a translation table"""
CIPHER = maketrans(ascii_lowercase, ascii_lowercase[::-1])


def _apply_cipher(text):
    return text.lower().translate(CIPHER, punctuation + whitespace)


def _blocks_of(text):
    """Takes in the encoded text and returns it as a single string with a whitespace inserted every 5 chars."""
    blocks = ''
    i = 0
    while i + 5 < len(text) - 1:
        blocks += text[i:i+5] + ' '
        i += 5
    if text[i:] != '':
        blocks += text[i:]
    return blocks


def encode(plaintext):
    encoded = _apply_cipher(plaintext)
    if len(encoded) > 5:
        return _blocks_of(encoded)
    else:
        return encoded


def decode(encoded):
    return _apply_cipher(encoded)
