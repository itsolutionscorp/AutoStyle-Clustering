def word_count(input):
	wordSet = input.replace('\n',' ').replace("  "," ").split(" ")
	wordSetSmall = list(set(wordSet))
	dictionaryCount = {i:sum(1 for j in wordSet if j == i) for i in wordSetSmall if i != ''}
	return dictionaryCount
