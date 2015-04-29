def replace(s):
	s = s.lower()
	first = ord('a')
	last = ord('z')
	baseOrd = first + last;
	rep = ''
	counter = 0
	for c in s:
		o = ord(c)
		if first <= o <= last:
			rep += chr(baseOrd - o)
		elif c in '0123456789':
			rep += c
	return rep	

def chunks(seq, length):
	return ' '.join([seq[i:i+length] for i in range(0, len(seq), length)])

def encode(s):
	return chunks(replace(s), 5)

def decode(s):
	return replace(s)
