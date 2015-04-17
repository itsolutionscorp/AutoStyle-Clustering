from collections import Counter
def word_count(string):
	wc_list = []
	word = ""
	for c in string:
		if c.isalnum():
			word = word + c
		else:
			if word:
				wc_list.append(word.lower())
				word = ''
	if word:
		wc_list.append(word.lower())
	return Counter(wc_list)
