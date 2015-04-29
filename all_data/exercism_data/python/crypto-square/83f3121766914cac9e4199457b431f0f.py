import math

def encode(plain):

	if not plain:
		return ''

	plain = ''.join(c for c in plain.lower() if c.isalnum())

	length = len(plain)
	width  = int(math.ceil(math.sqrt(length)))
	return ' '.join(plain[n::width] for n in xrange(width))
