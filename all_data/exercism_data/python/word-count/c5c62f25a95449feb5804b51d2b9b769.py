def word_count(phrase):
	strlist = (' '.join(phrase.split())).split(' ')
	print strlist
	dict = {}
	for word in strlist:
		dict[word] = (strlist.count(word))
	return dict
