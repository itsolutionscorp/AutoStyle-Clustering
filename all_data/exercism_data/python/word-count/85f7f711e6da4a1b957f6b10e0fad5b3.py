import re

def word_count(text):
	
	result = {}
	text = re.sub('[,|!|@|#|$|%|^|&|*|(|)|:]', '', text)
	splitArr = text.lower().split()

	for word in splitArr:
		if word in result:
			result[word]+=1
		else:
			result[word]=1
	return result
