import re
import string
from itertools import izip_longest

TRANS=string.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1])

def decode(s):
	s = re.sub(r'\W', '', s).lower()
	return string.translate(s, TRANS)

def encode(s):
	encoded = decode(s)
	grouped = izip_longest(*[iter(encoded)]*5, fillvalue='')
	return ' '.join(map(lambda x:''.join(x), grouped))
