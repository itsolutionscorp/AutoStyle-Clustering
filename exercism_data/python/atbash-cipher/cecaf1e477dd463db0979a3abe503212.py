import string

CODECHARS = "zyxwvutsrqponmlkjihgfedcba"
MESSCHARS = "abcdefghijklmnopqrstuvwxyz"
TBL = string.maketrans(MESSCHARS, CODECHARS)

def encode(inputstring):
	inputstring = inputstring.lower().translate(None, string.punctuation+' ')
	inputstring = inputstring.translate(TBL)
	outputstring = ""

	for i in range(0, len(inputstring), 5):
		outputstring = outputstring + inputstring[i:i+5] + ' '

	return outputstring.rstrip()


def decode(inputstring):
	inputstring = inputstring.translate(None, ' ')
	return inputstring.translate(TBL)
