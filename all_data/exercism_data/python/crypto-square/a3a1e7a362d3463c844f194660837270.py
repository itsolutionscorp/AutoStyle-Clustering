import math
import re
from itertools import izip_longest

def encode(msg):
	msg = re.sub(r'\W','',msg).lower()
	square_size = int(math.ceil(len(msg)**0.5))
	square = izip_longest(*[iter(msg)] * square_size, fillvalue='')
	return " ".join(map(''.join, zip(*square)))
