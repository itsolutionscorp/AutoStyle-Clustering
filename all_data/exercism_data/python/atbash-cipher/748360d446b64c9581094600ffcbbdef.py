def encode(message): 
	codedmessage = ''
	for c in message.lower():
		codedmessage += swap_char(c)
	return codedmessage


def decode(message):
	decodedmessage = ''
	for c in message.lower():
		if c != " ":
			decodedmessage += swap_char(c)
	return decodedmessage

def swap_char(c):
	if c.isalpha():
		order = ord(c) - 98
		return chr(96 + abs(order - 25))
	else:
		return c
