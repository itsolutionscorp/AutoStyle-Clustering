from collections import Counter

def detect_anagrams(word, potentials):
	word_counter = Counter([x for x in word.lower()])
	out_list = []
	for test_word in potentials:
		if test_word.lower() != word.lower():
			test_counter = Counter([x for x in test_word.lower()])
			if compare_counters(word_counter, test_counter):
				out_list.append(test_word)
	return out_list

def compare_counters(count1, count2):
	'''
	Compares two collections.Counter's and makes sure they have the same elements
	and each element appears the same number of times
	'''
	return True if count1.most_common() == count2.most_common() else False
