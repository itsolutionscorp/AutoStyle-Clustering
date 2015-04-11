import string

class Caesar:
	ENCODE = 1
	DECODE = -1

	def __init__(self, key="d"):
		self.key = map(lambda c:ord(c) - ord('a'), key)

	def shift_char(self, direction, (pos, c)):
		c = ord(c) - ord('a') + direction * self.key[pos % len(self.key)]
		return chr(c % 26 + ord('a')) 

	def do_code(self, direction, msg):
		msg = [c for c in msg.lower() if c in string.lowercase]
		return ''.join( map(
			lambda tup:self.shift_char(direction, tup),
			 enumerate(msg) ) )

	def encode(self, msg):
		return self.do_code(Caesar.ENCODE, msg)

	def decode(self, msg):
		return self.do_code(Caesar.DECODE, msg)
		

class Cipher(Caesar):
	pass
