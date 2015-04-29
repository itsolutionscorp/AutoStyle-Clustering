def word_count(text):
	cleantext = ""
	for c in text:
		if c.isspace() or c.isalpha() or c.isdigit():
			cleantext += c
	wordcount = {}
	for w in cleantext.lower().split():
		count  = wordcount.get(w, 0)
		count += 1
		wordcount[w] = count
	return wordcount
