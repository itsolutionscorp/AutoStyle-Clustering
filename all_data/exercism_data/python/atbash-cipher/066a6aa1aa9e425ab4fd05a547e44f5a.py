__author__ = 'Hinek'

ALPHABET = '1234567890abcdefghijklmnopqrstuvwxyz0987654321'

def encode(text, spaces=True):
    result = ''
    text = ''.join(ch for ch in text if ch.isalnum())
    for t in list(text.lower()):
        if spaces and (len(result)+1) % 6 == 0:
            result += ' '
        result += ALPHABET[::-1][ALPHABET.index(t)]
    return result

def decode(text):
    return encode(text, spaces=False)
