import string

TRANSLATION = string.maketrans(
    string.ascii_lowercase + string.ascii_uppercase,
    string.ascii_lowercase[::-1] * 2
)

CHARS_TO_REMOVE = string.punctuation + string.whitespace

def encode(plain_text):
    encoded = _apply_cipher(plain_text)
    chunks_of_5_characters = [
        encoded[offset : offset+5]
        for offset in xrange(0, len(encoded), 5)
    ]
    return " ".join(chunks_of_5_characters)

def decode(encoded_text):
    return _apply_cipher(encoded_text)

def _apply_cipher(text):
    return text.translate(TRANSLATION, CHARS_TO_REMOVE);
