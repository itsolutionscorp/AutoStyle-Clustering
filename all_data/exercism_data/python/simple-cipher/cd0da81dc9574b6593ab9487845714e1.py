'''
Simple shift cipher (like Caesar) and a more secure substitution cipher
'''

import random

alphalower = 'abcdefghijklmnopqrstuvwxyz'

def rotchar(char, keychar, direc='fwd'):
	# rotate char through alphabet according to keychar
	if direc=='rev':
		z = ord(char)-ord('a') - (ord(keychar)-ord('a'))
		return (alphalower*2)[z + len(alphalower)]
	else:
		z = ord(char)-ord('a') + (ord(keychar)-ord('a'))
		return (alphalower*2)[z]

class Cipher:

	def __init__(self, *cipherkey):
		if len(cipherkey)>0:
			self.cipherkey = cipherkey[0]
		else:	# generate random 100-character key
			random.seed()
			self.cipherkey = ''.join([random.choice(alphalower) for ii in range(100)])

	def encode(self, plaintext):
		plaintext = [ch.lower() for ch in plaintext if ch.isalpha()]
		codedtext = [rotchar(ch,self.cipherkey[ii%len(self.cipherkey)],'fwd')
			for (ii,ch) in enumerate(plaintext)]
		return ''.join(codedtext)

	def decode(self, codedtext):
		plaintext = [rotchar(ch,self.cipherkey[ii%len(self.cipherkey)],'rev')
			for (ii,ch) in enumerate(codedtext)]
		return ''.join(plaintext)

class Caesar(Cipher):

	def __init__(self):
		self.cipherkey = 'd'

if __name__=='__main__':
	e = Cipher('everclear').encode('dast')
	print(e)
	print(Cipher('everclear').decode(e))
	print(Caesar().encode('go'))
	print(Caesar().decode('abcd'))
