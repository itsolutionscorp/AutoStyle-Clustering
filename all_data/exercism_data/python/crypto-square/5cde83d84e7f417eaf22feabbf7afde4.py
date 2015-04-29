import math

def encode(iStr):
	if iStr == '':
		return ''
	nStr = ''.join([c.lower() for c in iStr if c.isalpha() or c.isdigit()])
	cipherText=''
	nCol = int(math.floor(math.sqrt(len(nStr)-1)))+1
	nLin = len(nStr)/nCol+1
	for i in range(nCol):
		for j in range(nLin):
			if i+j*nCol < len(nStr):
				cipherText+=nStr[i+j*nCol]
		cipherText+=' '
	return cipherText[:-1]
