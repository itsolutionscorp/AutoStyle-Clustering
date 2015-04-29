import re

ALPH = 'abcdefghijklmnopqrstuvwxyz'
NUMS = '0123456789'
CHARS = list(zip(''.join((ALPH, NUMS)), ''.join((ALPH[::-1], NUMS))))
ENCODEMAP = dict(CHARS)
DECODEMAP = dict((b, a) for a, b in CHARS)

def encode(phrase):
    encoded = ''.join(ENCODEMAP[char] for char in re.sub('(?![a-z]|[0-9]).', '', phrase.lower()))
    return ' '.join(encoded[i:i+5] for i in range(0, len(encoded), 5))

def decode(phrase):
    decoded = ''.join([DECODEMAP[char] for char in re.sub('(?![a-z]|[0-9]).', '', phrase)])
    return decoded
