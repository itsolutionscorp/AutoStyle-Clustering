import string, random

class Caesar:
	def __init__(self):
		self.alph = list(string.ascii_lowercase)
	
	def getInd(self,i,k):
		return (i+k)%26
	
	def encode(self,s):
		return ''.join([self.alph[self.getInd(self.alph.index(x),3)] for x in list(s.lower()) if x.isalpha()])
	
	def decode(self,s):
		return ''.join([self.alph[self.getInd(self.alph.index(x),-3)] for x in list(s.lower()) if x.isalpha()])

	
class Cipher:
	def __init__(self,key = None):
		self.alph = list(string.ascii_lowercase)
		if key == None:
			self.key = []
			for x in range(0,100):
				self.key.append(self.alph[random.randint(0,25)])
		else :
			self.key = list(key)
		
	
	def getKey(self,n):
		return self.alph.index(self.key[n % len(self.key)])
	
	def getInd(self,i,k):
		return (i+k)%26
	
	
	def encode(self,s):
		return ''.join([self.alph[self.getInd(self.alph.index(x),self.getKey(ind))] for ind,x in enumerate(list(s.lower())) if x.isalpha()])
	
	def decode(self,s):
		return ''.join([self.alph[self.getInd(self.alph.index(x),-self.getKey(ind))] for ind,x in enumerate(list(s.lower())) if x.isalpha()])
