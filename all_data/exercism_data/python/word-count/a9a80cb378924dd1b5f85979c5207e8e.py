#import string
def word_count(str):
	words = {}
	for word in str.strip().split():
		# Disabled as it fails the test, but I believe that's a test error,
		# this should be enabled.
		# word = word.strip(string.punctuation)

		if word in words:
			words[word] += 1
		else:
			words[word] = 1

	return words
