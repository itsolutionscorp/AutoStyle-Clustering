from math import sqrt, ceil
import re
from itertools import izip_longest

def transpose_and_flatten(matrix):
	return ' '.join(map(''.join, izip_longest(*matrix, fillvalue='')))

def encode(msg):
	msg = re.sub(r'\W','',msg).lower()
	square_size = int(ceil(sqrt(len(msg))))
	square = izip_longest(*[iter(msg)] * square_size, fillvalue='')
	return transpose_and_flatten(square)

def decode(msg):
	return transpose_and_flatten(msg.split())
	
