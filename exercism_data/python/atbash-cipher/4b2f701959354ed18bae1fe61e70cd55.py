alpha = [chr(i) for i in range(97,123)] #lower cases
rev_alpha = list(reversed(alpha))
digits = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

def encode(string):
	string = [x.lower() for x in string if x.lower() in alpha or x in digits]
	enc = ''
	for i, char in enumerate(string):
		i += 1
		enc += rev_alpha[alpha.index(char)] if char in alpha else char
		enc = enc if i % 5 != 0 else enc + ' '
	return enc.strip()

def decode(string): # Note: decode doesn't work for numbers.
	alpha_mapping = dict( zip( alpha, rev_alpha) )
	string = ''.join(string.split())
	return ''.join( [alpha_mapping[i] for i in string] )
