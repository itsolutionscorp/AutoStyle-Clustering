def decode(to_decode):
	Plain = 'abcdefghijklmnopqrstuvwxyz'
	Cipher = 'zyxwvutsrqponmlkjihgfedcba'
	mapping = {a:b for a,b in zip(Plain, Cipher)}
	decoded = [mapping[x] for x in to_decode if x in Plain]
	
	return ''.join(decoded)
	
	
def encode(to_encode):
	#Declare variables
	to_ignore = [',', '.', ' ']
	encoded = []
	
	#Map Cipher info
	Plain = 'abcdefghijklmnopqrstuvwxyz1234567890'
	Cipher = 'zyxwvutsrqponmlkjihgfedcba1234567890'
	mapping = {a:b for a,b in zip(Plain, Cipher)}
	
	#convert to lower first
	to_encode = to_encode.lower()
	
	#then do list comprehension and add spaces
	for index, val in enumerate([mapping[x] for x in to_encode if x not in to_ignore]):
		encoded.append(val)
		if (index+1) % 5 == 0:
			encoded.append(' ')
	
	return (''.join(encoded)).rstrip()
	
