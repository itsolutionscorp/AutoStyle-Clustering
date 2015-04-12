import string
def word_count(sentence):
	wordCount = {}
	words = sentence.split()

	# Removes Duplicate Words
	for word in sentence.split():
		# Remove punctuation and lower
		word = ''.join(ch for ch in word if ch not in set(string.punctuation)).lower();
		if word != "":
			if word in wordCount:
				wordCount[word] +=1;
			else:
				wordCount[word] = 1;
			
	print(wordCount);
	return wordCount;