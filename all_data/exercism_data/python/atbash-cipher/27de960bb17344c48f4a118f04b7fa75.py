#!/usr/bin/python -tt

alphabet='abcdefghijklmnopqrstuvwxyz'
ralphabet=alphabet[::-1]

def opp(c):
	if c.isalpha():
		return ralphabet[alphabet.index(c)]
	elif c.isdigit():
		return c
	else:
		return False

def decode(cypherText):
	out=''
	for c in cypherText.replace(' ',''):
		out+=opp(c)
	return out
	
def encode(text):
	out=''
	i=0
	for c in text.replace(' ','').lower():
		nChar = opp(c)
		if nChar:
			if i%5 == 0 and i != 0:
				out+=' '
			out+=nChar
			i+=1
	return out	
