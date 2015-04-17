def word_count(input):
	wordSet = input.replace('\n',' ').replace("  "," ").split(" ")
	return {i:sum(1 for j in wordSet if j == i) for i in list(set(wordSet)) if i != ''}
