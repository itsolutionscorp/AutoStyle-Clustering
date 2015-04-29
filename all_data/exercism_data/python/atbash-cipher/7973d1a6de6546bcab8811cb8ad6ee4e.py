import itertools
import string

ENCODING_TRANSTABLE = str.maketrans(string.ascii_letters, 2 *
        string.ascii_lowercase[::-1], string.punctuation + ' ')
DECODING_TRANSTABLE = str.maketrans(string.ascii_lowercase[::-1],
        string.ascii_lowercase, ' ')

def encode(text):
    text = text.translate(ENCODING_TRANSTABLE)
    return ' '.join(text[i:i + 5] for i in range(0, len(text), 5))

def decode(text):
    return text.translate(DECODING_TRANSTABLE)
