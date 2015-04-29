### Word Count Submission File ###

def word_count(sentence):
	wordcount = {}
	for word in sentence.split():
		if word not in wordcount:
			wordcount[word] = 1
		else:
			wordcount[word] += 1

	return wordcount
