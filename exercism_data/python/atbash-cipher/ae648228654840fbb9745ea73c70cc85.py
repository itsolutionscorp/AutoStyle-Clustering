import string

ALPHABET = string.ascii_lowercase
EXCLUDED = string.punctuation + string.whitespace
ATBASH = str.maketrans(ALPHABET, ALPHABET[::-1], EXCLUDED)

def encode(text):
    text = text.lower().translate(ATBASH)
    return ' '.join(text[i : i + 5] for i in range(0, len(text), 5))

def decode(text):
    return text.lower().translate(ATBASH)
