def encode(message): 
	codedmessage = ''
	for c in message.lower():
		codedmessage += swap_char(c)
	
	return " ".join(codedmessage[i:i+5] for i in range(0, len(codedmessage),5))


def decode(message):
	decodedmessage = ''
	for c in message.lower():
		if c != " ":
			decodedmessage += swap_char(c)
	return decodedmessage

def swap_char(c):
	if c.isalpha():
		return chr( ord('a') + (ord('a') - ord(c) - 1)%26)
	elif c.isdigit():
		return c
	else:
		return ""
