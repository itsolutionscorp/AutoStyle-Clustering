from math import sqrt, ceil

def encode(msg):
	if not msg.strip():
		return ""
	formatted_msg = "".join([i.lower() for i in msg if i.isalpha() or i.isdigit()])
	a = int(ceil(sqrt(len(formatted_msg))))
	split_msg = [list(formatted_msg[a*i:a*(i+1)]) for i in xrange(len(formatted_msg)//a + 1)]
	i = 0
	encoded = ''
	while split_msg[0]:
		for j in xrange(len(split_msg)):
			if not i % 5 and i != 0 and split_msg[j]:
				encoded += ' '
			if split_msg[j]:
				encoded += split_msg[j].pop(0)
				i += 1
	return encoded

def decode(msg):
	msg = "".join([i for i in msg.split()])
	cols = int(ceil(sqrt(len(msg))))
	rows = int(ceil(len(msg)/float(cols)))  
	leftovers = len(msg) % cols if len(msg) % cols else cols
	decoded = ""
	for i in xrange(rows):
		cols = leftovers if (i == rows - 1) else cols
		for j in xrange(cols):
			decoded += msg[i + j*rows] if j <= leftovers else msg[i + j*(rows - 1) + leftovers]
	return decoded
