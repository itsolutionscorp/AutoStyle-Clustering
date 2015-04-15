# counts the occurrences of each word in the given phrase.
# a word within a phrase is here defined as any substring consisting of at least one character preceded and succeeded by the beginning of the string, end of the string, or whitespace
# phrase must be of type str
def word_count(phrase):
	word_count = {}
	for word in phrase.split():
		word_count[word]=word_count.get(word,0)+1
	return word_count
