

def encode(string):
	alpha = 'abcdefghijklmnopqrstuvwxyz'
	string = string.lower()
	nospace = ''.join(string.split())
	noper = ''.join(nospace.split('.'))
	finalstr = ''.join(noper.split(","))
	coded = []
	count = 1
	for character in finalstr:
		try:
			place = alpha.index(character) + 1
			coded += (alpha[-place])
		except:
			coded += character
	coded = ''.join(coded)	
	return ' '.join(coded[i:i+5] for i in range(0, len(coded), 5))

def decode(string):
	alpha = 'zyxwvutsrqponmlkjihgfedcba'
	nospace = ''.join(string.split())
	decoded = ''
	for character in nospace:
		try:
			place = alpha.index(character) + 1
			decoded += (alpha[-place])
		except:
			decoded += character
	return decoded
	
