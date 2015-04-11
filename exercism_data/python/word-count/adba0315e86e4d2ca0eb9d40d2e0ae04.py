def split_args(argList):
	# What if the arguments are passed as multiple strings? Split them up into
	# a single list.
	
	wordList = []
	for arg in argList:
		currentArg = arg.split()
		for arg in currentArg:
			wordList.append(arg)
	return wordList
		
def word_count(*args):
	wordList = split_args(list(args))
	wordCounts = {}
	
	while len(wordList) > 0:
		count = 1 # We'll always have at least one instance of the word.
		word = wordList.pop()
		while word in wordList:
			wordList.pop(wordList.index(word))
			count += 1
		wordCounts[word] = count
	return wordCounts
	
