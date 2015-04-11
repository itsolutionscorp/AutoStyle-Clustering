import string
import re

intab = string.ascii_letters
outtab= string.ascii_lowercase[::-1]*2

trantab = str.maketrans(intab,outtab)

def encode(word):
	########-- translate ----------remove spaces
	word = word.translate(trantab).replace(" ","")

	#remove punctuation
	allow = string.ascii_lowercase + string.digits
	word = re.sub('[^%s]' % allow, '', word)
	#use regex to substitute non letters or digits to empty strings

	#add spaces every 5 chars
	index_adjuster = 0
	for i in range(5,len(word)-1,5):
		word = word[0:i+index_adjuster] + " " + word[i+index_adjuster:]
		index_adjuster+=1

	return word
	
def decode(word):
	#return--- translate ----------remove spaces
	return word.translate(trantab).replace(" ","")
