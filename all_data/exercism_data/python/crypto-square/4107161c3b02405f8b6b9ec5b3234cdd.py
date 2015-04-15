from __future__ import division
from math import sqrt, ceil, floor
import re

pattern = re.compile('[^a-zA-Z0-9]')


def encode(message):
	message = re.sub(pattern, '', message.lower())
	size = int(ceil(sqrt(len(message))))

	if size == 0:
		return ''

	message = message.ljust(size ** 2)
	
	encoded = ''.join(message[j*size+i] for i in range(size) for j in range(size)).replace(' ', '')
	return ' '.join(encoded[i:i + 5] for i in range(0, len(encoded), 5))


def decode(message):
	message = re.sub(pattern, '', message.lower())
	size = int(ceil(sqrt(len(message))))

	if size == 0:
		return ''

	width = size - ((size**2 - len(message)) // size)

	square = []
	sep = (len(message) % size)
	for i in range(0, sep * width, width):
		square.append(list(message[i:i + width].ljust(size)))

	nwidth = (len(message) - sep*width) // (size - sep)
	for i in range(sep * width, len(message), nwidth):
		square.append(list(message[i:i + nwidth].ljust(size)))

	return ''.join(square [j][i] for i in range(size) for j in range(size)).replace(' ', '')
