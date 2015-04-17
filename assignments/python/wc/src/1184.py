import re
def word_count(sentence):
	cleaned_words = [re.sub("[\W]","",word).lower() for word in sentence.split()]
	counted_words = {}
	for word in cleaned_words:
		if word:
			try: 
				counted_words[word] = counted_words[word] + 1	
			except KeyError:
				counted_words[word] = 1
	return counted_words
