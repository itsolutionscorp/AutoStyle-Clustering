from collections import Counter

def word_count(string):
	wc_list = []
	word = ""
	wc_dict = Counter()
	
	for c in string:
		if c.isalnum():
			word = word + c
		else:
			if word:
				wc_list.append(word)
				word = ''
	if word:
		wc_list.append(word)
	
	for word in wc_list:
		wc_dict[word.lower()] += 1
	return wc_dict
