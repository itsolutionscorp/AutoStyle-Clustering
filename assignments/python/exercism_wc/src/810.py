def word_count(sen):
	sen = " ".join(sen.split())
	words = sen.split(" ")
	ans = {}
	for word in words:
		if word not in ans.keys():
			ans[word] = words.count(word)
	return ans