def word_count(IString):
	l = {}
	for word in IString.split():
		try:
			l[word] = l[word]+1
		except:
			l[word] = 1
	return l
