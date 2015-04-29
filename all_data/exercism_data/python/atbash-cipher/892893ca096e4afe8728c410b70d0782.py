import re, string

def decode(inputStr):
	inputStr = re.sub("[^a-z^0-9]",'',inputStr.lower())
	Cipher = "zyxwvutsrqponmlkjihgfedcba"
	decrypted = ""
	for x in inputStr:
		if(x in string.digits):
			decrypted += x
		else:
			decrypted += Cipher[(ord(x)-ord('a'))]

	return decrypted


def encode(inputStr):
	inputStr = re.sub("[^a-z^0-9]",'',inputStr.lower())
	
	Cipher = "zyxwvutsrqponmlkjihgfedcba"
	encrypted = ""
	finalenpt = ""
	for x in inputStr:
		if(x in string.digits):
			encrypted += x
		else:
			encrypted += Cipher[(ord(x)-ord('a'))]
	for x in xrange(len(encrypted)):
		if((1+x)%5 == 0 and x != len(encrypted)-1):
			finalenpt += encrypted[x] + " "
		else:
			finalenpt += encrypted[x]
	return finalenpt
