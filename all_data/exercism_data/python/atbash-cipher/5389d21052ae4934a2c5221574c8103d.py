from re import compile, sub

ordA = ord('a')
ordZ = ord('z')
chunksize = 5
trimPattern = compile(r'[^a-z0-9]')

def encode(phrase):
    encoded = decode(phrase)
    return ' '.join(encoded[i:i + chunksize] for i in range(0, len(encoded), chunksize))

def decode(phrase):
    phrase = sub(trimPattern, '', phrase.lower())
    return ''.join(chr(ordA + ordZ - ord(c)) if c.isalpha() else c for c in phrase)
