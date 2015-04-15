from string import maketrans
from re import sub

intab = "abcdefghijklmnopqrstuvwxyz"
outtab = intab[::-1]

def decode(clear_text):
	clear_text = "".join(clear_text.split())
	clear_text = clear_text.lower()
	trantab = maketrans(intab, outtab)
	return remove_tokens(clear_text.translate(trantab)).strip()


def encode(cypher_text):
	cypher_text = "".join(cypher_text.split())
	cypher_text = cypher_text.lower()
	trantab = maketrans(outtab, intab)
	return grouping(remove_tokens(cypher_text.translate(trantab))).strip()


def grouping(text):
	"""Groups into 5 characters long units and seperates with a whitespace"""
	sequence = ""
	for x in range(len(text)):
		if x % 5 == 0:
			sequence += " "
			sequence += text[x]
		else:
			sequence += text[x]
	return sequence

def remove_tokens(text):
	"""Removes any non-alpernumerical"""
	return sub(r'\W', '', text)
