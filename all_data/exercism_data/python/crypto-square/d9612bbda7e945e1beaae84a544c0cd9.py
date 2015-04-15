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
	
def decode(iStr):
	if iStr=='':
		return ''
	nStr = ''.join([c.lower() for c in iStr if c.isalpha() or c.isdigit()])
	
	def count_lines(iStr):
		n=0
		while iStr[n] != ' ' and n < len(iStr):
			n+=1
		return n
	
	plain=''
	nLin = count_lines(iStr)
	nCol = len(nStr)/nLin+1
	for i in range(nLin):
		for j in range(nCol):
			if i+j*nLin < len(nStr):
				plain+=nStr[i+j*nLin]
	return plain
	
if __name__ == '__main__':
	print encode("abcdefghijk")
	print decode(encode("abcdefghijk"))
