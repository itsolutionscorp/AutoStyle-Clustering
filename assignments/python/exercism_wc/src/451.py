def word_count(text):
	words = text.split()
	wrd_count = {}
	for i in words:
		wrd_count[i] = wrd_count.get(i,0) + 1
	return wrd_count
