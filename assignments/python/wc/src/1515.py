import re
import collections
def word_count(sentence):
	sentence, n = re.subn(r"[^\w\s]", "", sentence.lower())
	words = [w for w in sentence.split(" ") if w]
	return dict(collections.Counter(words))
