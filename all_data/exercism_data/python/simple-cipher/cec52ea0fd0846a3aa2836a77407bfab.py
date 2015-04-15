from string import ascii_lowercase
from random import choice
from itertools import izip, cycle


def transpose_encode(a, b):
	return chr(ord('a') + (ord(a) + ord(b) - 2 * ord('a')) % 26)


def transpose_decode(a, b):
	return chr(ord('a') + (ord(a) - ord(b)) % 26)


def random_key():
	return ''.join(choice(ascii_lowercase) for i in xrange(100))


class CipherBase(object):

	def xform(self, input, xpose):
		return ''.join([
			xpose(char, key)
			for char, key in izip(input.lower(), cycle(self.key))
			if char.isalpha()
		])

	def encode(self, plain):
		return self.xform(plain, transpose_encode)

	def decode(self, cipher):
		return self.xform(cipher, transpose_decode)


class Caesar(CipherBase):

	def __init__(self):
		self.key = 'd'


class Cipher(CipherBase):

	def __init__(self, key=None):
		self.key = key if key else random_key()
