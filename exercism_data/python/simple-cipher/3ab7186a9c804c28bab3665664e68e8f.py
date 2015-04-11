class Cipher:
	# Attempting to keep the import private, within scope, _randrange
	def __init__(self, key=None):
		from random import seed as _seed
		from time import time as _time
		from random import randrange as _randrange
		_seed(_time())
		if key == None:
			self.key = ''.join(chr(_randrange(97,123)) for i in range(100))
		elif not all(ord(i) in range(97,123) for i in key) or any(i.isdigit() for i in key):
			raise ValueError("Key must consist of lowercase alphabet characters")
		else:
			self.key = key

	def encode(self,text):
		text = _lowerize(text)
		return ''.join(chr(_sumChr(text[i],self.key[i%len(self.key)])) for i in range(len(text)))

	def decode(self,text):
		text = _lowerize(text)
		return ''.join(chr(_difChr(text[i],self.key[i%len(self.key)])) for i in range(len(text)))


class Caesar(Cipher):
	# Calls the simple cipher with the key 'd'
	def __init__(self):
		Cipher.__init__(self,'d')


def _lowerize(text):
	# According to PEP 8, a "_" function is not imported when the module 
	# is imported. Makes it more of a "private" function

	# ord(str of len 1) returns the value of the ascii character in some 
	# character map or another text.lower() is the lowercase-ing method for a string
	# range(93,123) returns ord numbers for a through z (93 to 122)
	textClean = ''.join(i for i in text.lower() if ord(i) in range(97,123) )
	return textClean

def _sumChr(a,b):
	return (ord(a)-97 + ord(b)-97)%26 + 97

def _difChr(a,b):
	return (  ord(a) - ord(b))%26 +97
