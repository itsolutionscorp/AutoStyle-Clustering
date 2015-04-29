import string
import random

class Caesar(object):

	alph = string.ascii_lowercase
	alph_shifted = alph[3:] + 'abc'
	decode_table = dict(zip(alph_shifted, alph))
	encode_table = dict(zip(alph, alph_shifted))

	def _translate(self, s, tr):
		return "".join(tr[c] for c in s.lower() if c.isalpha())

	def decode(self, s):
		return self._translate(s, self.decode_table)

	def encode(self, s):
		return self._translate(s, self.encode_table)

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

	def _translate(self, s, tr_fun):
		res = []
		for i, c in enumerate(s):
			res.append(tr_fun(c, self.key[i % len(self.key)]))
		return "".join(res)

	def encode(self, s):
		return self._translate(s, self._to)

	def decode(self, s):
		return self._translate(s, self._from)
