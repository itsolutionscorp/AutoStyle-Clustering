'''cipher.py
	created 6 Nov 2014
	by @jestuber '''
import string
from itertools import izip, cycle

class Caesar(object):
	"""docstring for Caesar"""
	def __init__(self):
		super(Caesar, self).__init__()
		# self.arg = arg
	def encode(self,plaintext):
		return Cipher().encode(plaintext)
	def decode(self,encoded):
		return Cipher().decode(encoded)



class Cipher(object):
	"""docstring for cipher"""
	def __init__(self, key='d'):
		super(Cipher, self).__init__()
		self.key = key

	def encode(self,plaintext):
		plaintext = plaintext.translate(None, string.punctuation+string.digits+' ').lower()
		return ''.join(shift(c,k) for c,k in izip(plaintext,cycle(self.key)))

	def decode(self,encoded):
		return ''.join(shift(c,k,True) for c,k in izip(encoded,cycle(self.key)))		



def shift(letter,key,inv=False):
	'''shift letter by n alphabet characters, by -n if inv=true'''
	newkey = string.lowercase.index(letter) + \
		string.lowercase.index(key)*(-1 if inv else 1)
	if newkey > 25:
		newkey -= 26
	if newkey < 0:
		newkey += 26

	return string.lowercase[newkey]
