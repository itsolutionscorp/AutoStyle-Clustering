# count the number of words in a string

# create a list containing all the words in a string, with punctuation removed
def list_words_in_string(phrase):
	word_list = []
	word = ''
	start, end = 0, 0
	n = True
	i = 0

	for c in phrase:
		if c.isalnum():
			if n:
				start = i
				n = False
		else:
			if (not n):
				end = i
				word_list.append(phrase[start:end])
				n = True
		i += 1

	if phrase[-1].isalnum():
		word_list.append(phrase[start:])

	return word_list

# count the occurrences of each case-insensitive word in a string
# puncuation marks and whitespace are ignored, and numeric sequences are words
def word_count(message):
	word_list = list_words_in_string(message)
	word_counts = dict()

	for word in word_list:
		if word in word_counts:
			word_counts[word] += 1
		else:
			word_counts[word] = 1

	return word_counts
