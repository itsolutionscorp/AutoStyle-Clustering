def word_count(sen):
#Takes a sentence and returns a dictionary with each
#word and the frequency each word
	sen = " ".join(sen.split())
	#Gets rid of all extra spaces, \t's and \n's
	words = sen.split(" ")
	ans = {}

	for word in words:
		if word not in ans.keys():
			ans[word] = words.count(word)
			
	return ans
