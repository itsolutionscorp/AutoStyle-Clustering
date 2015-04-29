def word_count(word):
	word_arr = word.split()
	#print word_arr
	count_dict = {}
	for w in word_arr:
		if w not in count_dict:
			count_dict[w] = 1
		else:
			count_dict[w]+=1
	return count_dict
