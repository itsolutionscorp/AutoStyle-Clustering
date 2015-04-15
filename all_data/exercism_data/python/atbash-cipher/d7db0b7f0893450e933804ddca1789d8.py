import string

intab = string.ascii_letters
outtab= string.ascii_lowercase[::-1]*2

trantab = str.maketrans(intab,outtab)


def encode(word):
	########lowercase --- translate ---------------remove spaces
	word = word.lower().translate(trantab).replace(" ","")

	#remove punctuation
	for a in string.punctuation:
		word = word.replace(a,"")	

	#add spaces every 5 chars
	index_adjuster = 0
	for i in range(5,len(word)-1,5):
		word = word[0:i+index_adjuster] + " " + word[i+index_adjuster:]
		index_adjuster+=1

	return word
	
def decode(word):
	#return lowercase --- translate ---------------remove spaces
	return word.lower().translate(trantab).replace(" ","")
