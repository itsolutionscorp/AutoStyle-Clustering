from collections import Counter
import re
def word_count(phrase):
	phrase_lower = phrase.lower()
	phrase_lower_no_punctuation = re.sub(r'([^\w\s])+', '', phrase_lower)
	phrase_counts = Counter(phrase_lower_no_punctuation.split())
	return dict(phrase_counts.items())
