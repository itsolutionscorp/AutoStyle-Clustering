translate = {
	chr(index + ord('a')): chr(25 - index + ord('a'))
	for index in xrange(27)
}

for index in xrange(10):
	char = chr(index + ord('0'))
	translate[char] = char

def cipher(x):
	#	translate alphabetic characters, and skip the others
	return ''.join(translate[c] for c in x.lower() if c.isalnum())

def encode(x):
	y = cipher(x)

	#	split into groups of five
	return ' '.join(y[i:i+5] for i in range(0, len(y), 5))

def decode(x):
	return cipher(x)
