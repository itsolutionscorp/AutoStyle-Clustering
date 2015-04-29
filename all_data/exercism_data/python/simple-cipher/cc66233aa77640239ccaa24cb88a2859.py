import string
import random

class Caesar(object):

	alph = string.ascii_lowercase
	alph_shifted = alph[3:] + 'abc'
	decode_table = dict(zip(alph_shifted, alph))
	encode_table = dict(zip(alph, alph_shifted))

	def _translate(self, s, direction):
		tr = self.encode_table if direction == "to" else self.decode_table
		res = []
		for c in s.lower():
			if c.isalpha():
				res.append(tr[c])
		return "".join(res)

	def decode(self, s):
		return self._translate(s, "from")

	def encode(self, s):
		return self._translate(s, "to")

class Cipher(object):

	DEFAULT_LEN = 100

	def __init__(self, key=None):
		if key is None:
			self.key = "".join(chr(random.randrange(ord('a'), ord('z') + 1))
							   for x in range(self.DEFAULT_LEN))
		else:
			self.key = key

	def _to(self, c, key_char):
		return chr((ord(c) + ord(key_char) - 2 * ord('a')) % 26 + ord('a'))

	def _from(self, c, key_char):
		return chr((ord(c) - ord(key_char)) % 26 + ord('a'))

	def _translate(self, s, direction):
		tr_fun = self._to if direction == "to" else self._from
		res = []
		for i, c in enumerate(s):
			res.append(tr_fun(c, self.key[i % len(self.key)]))
		return "".join(res)

	def encode(self, s):
		return self._translate(s, "to")

	def decode(self, s):
		return self._translate(s, "from")
