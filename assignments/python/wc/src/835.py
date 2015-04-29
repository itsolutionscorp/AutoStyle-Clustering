import string
def word_count(phrase):
	table = string.maketrans('','')
	phrase = phrase.translate(table, string.punctuation)
	phrase = phrase.lower()
	word_list = phrase.split()
	counts = {}
	for word in word_list:
		try:
			counts[word] += 1
		except:
			counts[word] = 1
	return counts
