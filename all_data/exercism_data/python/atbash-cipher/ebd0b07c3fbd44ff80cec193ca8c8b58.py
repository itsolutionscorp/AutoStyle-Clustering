plain = "abcdefghijklmnopqrstuvwxyz"

def encode(text):
	encoded = ""
	if len(text) == 0:
		return ""
	text = text.lower()
	for k in range (0,len(text)):
		if text[k].isdigit():
			encoded = encoded + text[k]
		elif text[k].isalpha():
			encoded = encoded + plain[len(plain)-1-plain.find(text[k])]

		if len(encoded)>0 and (len(encoded)+1)%6 == 0:
			encoded = encoded + " "

	encoded = encoded.rstrip()
		
	return encoded
	
def decode(text):
	decoded = ""
	if len(text) == 0:
		return ""
		
	text = text.lower()	
	for k in range (0,len(text)):
		if text[k].isdigit():
			decoded = decoded + text[k]
		elif text[k].isalpha():
			decoded = decoded + plain[len(plain)-1-plain.find(text[k])]

	return decoded
