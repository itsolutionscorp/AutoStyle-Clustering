from string import maketrans
import re

pattern = re.compile(r'[\W]+')
ALPHABET = 'abcdefghijklmnopqrstuvwxyz'
trans = maketrans(ALPHABET, ALPHABET[::-1])

def encode(text):
    result = pattern.sub('', text)
    result = result.lower().translate(trans)
    return' '.join([result[x:x+5] for x in xrange(0, len(result), 5)])

def decode(text):
    return text.lower().translate(trans).replace(" ", "")
