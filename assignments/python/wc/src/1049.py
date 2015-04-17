import re
def word_count(phrase):
	wordCount = {}
	phrase = re.sub(r'(\S)\s+(\S)',r'\1 \2',phrase)
	for word in phrase.split(' '):
		if word in wordCount: 
			wordCount[word] += 1
		else:
			wordCount[word] = 1
	if '' in wordCount: wordCount.pop('')	# Handle multiple spaces; more efficient at lower concentrations
	return wordCount
