import collections
import string
def word_count(text):
	text_without_punctuation = text.translate(None, string.punctuation).lower()
	return collections.Counter(text_without_punctuation.split())
