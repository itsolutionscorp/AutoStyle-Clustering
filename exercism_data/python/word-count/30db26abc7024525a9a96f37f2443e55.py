from collections import Counter

def word_count(phrase):
	counts = Counter()
	p_list = phrase.replace('\n',' ').split(' ')
	
	for word in p_list:
		if word != '':
			counts[word] += 1
	
	return counts
