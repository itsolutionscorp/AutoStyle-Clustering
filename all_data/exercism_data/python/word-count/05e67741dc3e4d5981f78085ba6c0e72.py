import collections
import string

def word_count(raw_input):
	text = raw_input.translate(None, string.punctuation).strip().lower()
	return dict(collections.Counter(text.split()))
