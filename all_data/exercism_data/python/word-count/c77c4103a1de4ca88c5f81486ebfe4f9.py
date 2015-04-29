import string

def word_count(sentence):
	# Function to count occurrences of words in input string
	split_sentence = sentence.split()

	word_count = {}
	for word in split_sentence:
		word_count[word] = split_sentence.count(word)

	return word_count
