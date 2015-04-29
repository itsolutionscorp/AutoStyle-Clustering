import string
def word_count(phrase):
	count = {}  # Initialize dictionary to update in loop
	punct = set(string.punctuation)
	phrase = ''.join(x for x in phrase if x not in punct)
	line = phrase.lower().split() # Remove uppercase characters
	for word in line:
		times = line.count(word)
		count.update({word:times})
	return count
