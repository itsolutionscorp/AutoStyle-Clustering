import random 
class Cipher():
	def __init__(self,*args):
		self.alphabet={1:'a',2:'b',3:'c',4:'d',5:'e',
				6:'f',7:'g',8:'h',9:'i',10:'j',11:'k',12:'l',
				13:'m',14:'n',15:'o',16:'p',17:'q',18:'r',19:'s',20:'t',
				21:'u',22:'v',23:'w',24:'x',25:'y',26:'z'}
		self.lettershift={}
		for i in self.alphabet:
			self.lettershift[self.alphabet[i]]=i-1
		
		if len(args)!=0:
			self.key =args[0]	
			self.key =str(self.key)
		else:
			zufall=[random.randint(1, 26) for k in range(1,27)]
			self.key =[str(self.alphabet[k]) for k in zufall]
			
			
	def get_index(self,c):
		return 1 + self.lettershift[c]
		
	def shift(self,iterant):
		return self.lettershift[self.key[iterant]]
		
	def encode(self,text):
		text=text.lower()
		text=[k for k in text if k in 'abcdefghijklmnopqrtsuvwxyz']
		new=''
		while len(self.key)<len(text):
			self.key+=self.key
		iterant =0
		for c in text:
			index =1 +(self.get_index(c)+self.shift(iterant)-1)%26
			new+=self.alphabet[index]
			iterant+=1
		return new
			
	def decode(self,text):
		text=text.lower()
		text=[k for k in text if k in 'abcdefghijklmnopqrtsuvwxyz']
		old=''
		while len(self.key)<len(text):
			self.key+=self.key
		iterant=0
		for c in text:
			index=1 +(self.get_index(c)-self.shift(iterant)-1)%26
			old+=self.alphabet[index]
			iterant+=1
		return old


class Caesar(Cipher):
	def __init__(self):
		self.key='d'
		self.alphabet={1:'a',2:'b',3:'c',4:'d',5:'e',
				6:'f',7:'g',8:'h',9:'i',10:'j',11:'k',12:'l',
				13:'m',14:'n',15:'o',16:'p',17:'q',18:'r',19:'s',20:'t',
				21:'u',22:'v',23:'w',24:'x',25:'y',26:'z'}
		self.lettershift={}
		for i in self.alphabet:
			self.lettershift[self.alphabet[i]]=i-1
		#print self.lettershift
