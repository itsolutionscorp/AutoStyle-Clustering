import string
def word_count(s):
	words = [i for i in s.translate(string.maketrans("", ""), string.punctuation).lower().split()]
	return {word: words.count(word) for word in set(words)}
