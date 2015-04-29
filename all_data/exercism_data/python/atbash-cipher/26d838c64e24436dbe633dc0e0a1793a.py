def convert_char(c):
	if c.isalpha():
		return chr(ord('a') - ord(c.lower()) + ord('z'))
	elif c.isdigit():
		return c
	else:
		return ''

def encode(s):
	encoded = ''
	i = 0
	for c in s:
		new = convert_char(c)
		if new:
			encoded += new
			i += 1
			if i % 5 == 0:
				encoded += ' '
	return encoded.rstrip()

def decode(s):
	return ''.join(map(convert_char, s))
