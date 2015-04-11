import re
import string

CHARS = string.ascii_lowercase
ANTICHARS = ''.join(reversed(CHARS))

ENCODE = str.maketrans(CHARS, ANTICHARS)
DECODE = str.maketrans(ANTICHARS, CHARS)

def encode(phrase):
    encoded = re.sub('(?![a-z]|[0-9]).', '', phrase.lower()).translate(ENCODE)
    return ' '.join(encoded[i:i+5] for i in range(0, len(encoded), 5))

def decode(phrase):
    decoded = re.sub('(?![a-z]|[0-9]).', '', phrase.lower()).translate(DECODE)
    return decoded
