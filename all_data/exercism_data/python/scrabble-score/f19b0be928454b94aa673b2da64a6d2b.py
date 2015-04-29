import re

t = { 'AEIOULNRST' : 1, 'DG' :  2, 'BCMP' : 3, 
		'FHVWY' : 4, 'K' : 5, 'JX' : 8, 'QZ' : 10 }

sc = { c : value for chars, value in t.items() for c in chars }

def score(text):
	return sum( sc.get(t, 0) for t in text.upper() )
