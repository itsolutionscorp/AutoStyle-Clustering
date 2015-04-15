import string

def word_count(phrase):
	#remove punctuation
	punct = set(string.punctuation)
	phrase = ''.join(x for x in phrase if x not in punct)

	#split into words
	words = phrase.split()

	#count words
	word_count = {}
	for word in words:
		
		word = word.lower()
		if word in word_count:
			word_count[word] += 1
		else:
			word_count[word] = 1

	return word_count
