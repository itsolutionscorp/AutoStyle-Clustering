'''cipher.py
	created 6 Nov 2014
	by @jestuber '''
import string

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
		self.shift = [string.lowercase.index(c) for c in key]
	def encode(self,plaintext):
		encoded = []
		plaintext = plaintext.translate(None, string.punctuation+string.digits+' ').lower()
		ishift = 0
		for c in plaintext:
			plainkey = string.lowercase.index(c)
			
			newkey = plainkey + self.shift[ishift] 
			if newkey > 25:
				newkey -= 26

			encoded.append(string.lowercase[newkey])
			ishift = 0 if ishift>=len(self.shift)-1 else ishift+1

		return ''.join(encoded)

	def decode(self,encoded):
		plaintext = []
		encoded = encoded.translate(None, string.punctuation+string.digits+' ').lower()
		ishift = 0
		for c in encoded:
			enckey = string.lowercase.index(c)
			
			newkey = enckey - self.shift[ishift]
			if newkey < 0:
				newkey += 26

			plaintext.append(string.lowercase[newkey])
			ishift = 0 if ishift>=len(self.shift)-1 else ishift+1


		return ''.join(plaintext)
		
		
