import collections
import string

def word_count(text):
	processed_text = text.translate(None, string.punctuation).lower()
	return collections.Counter(processed_text.split())
