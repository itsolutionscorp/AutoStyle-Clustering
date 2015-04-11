import re
from itertools import izip_longest

DICT=dict(zip('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba'))

def do_encode(s, sep_char):
	s = re.sub(r'\W', '', s).lower()
	encoded = map(lambda c:DICT.get(c, c) , s)
	grouped = izip_longest(*[iter(encoded)]*5, fillvalue='')
	return sep_char.join(map(lambda x:''.join(x), grouped))

def decode(s):
	return do_encode(s, '')

def encode(s):
	return do_encode(s, ' ')
