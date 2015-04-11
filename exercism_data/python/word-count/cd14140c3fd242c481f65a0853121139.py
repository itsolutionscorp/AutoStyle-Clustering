import re

def word_count(phrase):
    words = {}
    test = re.split(r"[ \n]+", phrase)
    print test
    for word in test:
	word.strip()
	if not word:
	    continue
	if word not in words:
	    words[word] = 1
	else:
	    words[word] += 1
    return words
