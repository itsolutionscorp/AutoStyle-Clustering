import re

KEY = '1234567890zyxwvutsrqponmlkjihgfedcba'
ALPHABET = '1234567890abcdefghijklmnopqrstuvwxyz'

def decode(msg):
	msg = re.sub('[\W]', '', msg)
	msg = list(msg.lower())
	msg = cipher(msg)
	return ''.join(msg)


def encode(msg):
	#msg = msg.replace(' ', '')
	msg = re.sub('[\W]', '', msg)
	msg = list(msg.lower())
	encryptedMsg = []

	for block in [msg[i:i+5] for i in range(0, len(msg), 5)]:
		encryptedMsg.append(cipher(block))

	msg = ' '.join(encryptedMsg)
	return msg


def cipher(msg):
	return ''.join(map(lambda x: KEY[ALPHABET.find(x)], msg))
