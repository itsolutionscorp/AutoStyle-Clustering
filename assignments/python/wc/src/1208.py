import string
def word_count(sentence):
	split_sentence = sentence.split()
	word_count = {}
	for word in split_sentence:
		word_count[word] = split_sentence.count(word)
	return word_count
