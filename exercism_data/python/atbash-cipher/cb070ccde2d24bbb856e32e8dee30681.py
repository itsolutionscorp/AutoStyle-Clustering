import re
from string import ascii_lowercase as asciil, maketrans

_PATTERN = re.compile('[\s\W_]+')
_TABLE = maketrans(asciil, asciil[::-1])


def encode(msg):
    encoded = _PATTERN.sub('', msg.lower()).translate(_TABLE)
    return " ".join([encoded[i:i+5] for i in xrange(0,len(encoded),5)])


def decode(msg):
    return msg.replace(' ', '').translate(_TABLE)
