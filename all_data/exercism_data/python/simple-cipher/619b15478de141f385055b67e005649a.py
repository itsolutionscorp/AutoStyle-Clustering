import random

class Caesar(object):
	def __init__(self):
		self.all = 'abcdefghijklmnopqrstuvwxyz'
	
	def encode(self, phrase):
		phrase = ''.join([x.lower() for x in phrase if x.isalpha()])
		return ''.join([self.all[(self.all.index(x)+3)%26] for x in phrase])
		
	def decode(self, phrase):
		phrase = ''.join([x.lower() for x in phrase if x.isalpha()])
		return ''.join([self.all[(self.all.index(x)-3)%26] for x in phrase])


class Cipher(object):
	def __init__(self, key=''):
		self.key = key
		self.all = 'abcdefghijklmnopqrstuvwxyz'
		self.keycheck()
	
	def keycheck(self):
		if self.key == '':
			self.makekey()
		else:
			for letter in self.key:
				if not letter.isalpha() or letter != letter.lower():
					raise ValueError('No capitals or numbers please!')
					
	def makekey(self):
		self.key = ''.join([random.choice(self.all) for i in range(100)])
			
			
	def encode(self, phrase):
		phrase = ''.join([x.lower() for x in phrase if x.isalpha()])
		return ''.join([self.all[(self.all.index(phrase[x])+(self.all.index(self.key[(x%len(self.key))])))%26] for x in range(len(phrase))])
		'''
		Let me break this down---
		start with this:
		''.join([self.all[self.all.index(phrase[x])] for x in range(len(phrase))]) -- 
					this will go through the phrase and for every position in the phrase, it will get the letter at
					that position, it's index in the alphabet and then add the letter at that index to the list which
					will then be joined together.
				then we add...
		''.join([self.all[(self.all.index(phrase[x])+(self.all.index(self.key[x])))%26] for x in range(len(phrase))]) --
					we add the value of the letter at the current key position to the value of our letter, and then do a
					mod 26, to ensure we remain within the bounds of the alphabet.
				finally, in case the phrase is longer than the key, we add a mod to len(key) so we stay within key bounds...
				resulting in...
		''.join([self.all[(self.all.index(phrase[x])+(self.all.index(self.key[(x%len(self.key))])))%26] for x in range(len(phrase))])			
		'''
		
	def decode(self, phrase):
		phrase = ''.join([x.lower() for x in phrase if x.isalpha()])
		return ''.join([self.all[(self.all.index(phrase[x])-(self.all.index(self.key[(x%len(self.key))])))%26] for x in range(len(phrase))])
