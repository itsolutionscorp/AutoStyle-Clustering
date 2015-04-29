import random
import string

class Caesar(object):
	
	def __init__(self):
		self.alphabet="abcdefghijklmnopqrstuvwxyz"
	
	def code(self,iStr,key):
		cypherText=""
		for c in iStr:
			if c.isalpha():
				cypherText += self.alphabet[(self.alphabet.index(c.lower())+key)%26]
		return cypherText
	
	def encode(self,iStr):
		return self.code(iStr,3)
		
	def decode(self,iStr):
		return self.code(iStr,-3)
		
class Cipher(object):
	
	def __init__(self,key=''.join(random.choice(string.ascii_lowercase) for i in range(100))):
		self.key=key
		self.alphabet="abcdefghijklmnopqrstuvwxyz"
		if self.key.lower() != self.key or not self.key.isalpha():
			raise ValueError("The key must contain only lowercase alphabetic characters")
		
	def codeOneChar(self,char,charKey):
		return self.alphabet[(self.alphabet.index(char.lower())+charKey)%26]
		
	def get_charKey(self,pos):
		return self.alphabet.index(self.key[pos%len(self.key)])
		
	def encode(self,iStr):
		cipherText=""
		for i in range(len(iStr)):
			if iStr[i].isalpha():
				cipherText+=self.codeOneChar(iStr[i],self.get_charKey(i))
		return cipherText
	
	def decode(self,iStr):
		plainText=""
		for i in range(len(iStr)):
			if iStr[i].isalpha():
				plainText+=self.codeOneChar(iStr[i],-self.get_charKey(i))
		return plainText
		
if __name__ == "__main__":
	TestCy = Cipher("dadad")
	print TestCy.encode("Hello")
