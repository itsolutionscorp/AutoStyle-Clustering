def word_count(phrase):
	punc = ['!',':',',','@','#','$','%','^','&','*']
	for char in punc:
		phrase = phrase.replace(char, '')
	wordcounts = {}
	words = phrase.split()
	for word in words:
		word = word.lower()
		if word not in wordcounts:
			wordcounts[word] = 0
		wordcounts[word]+=1
	return wordcounts
