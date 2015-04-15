from collections import Counter

def detect_anagrams(word, wordlist):
	word_Counter = Counter(word.lower())

	list_to_return = list()

	for test_word in wordlist:
		if word_Counter == Counter(test_word.lower()) and test_word.lower() != word.lower():
			list_to_return.append(test_word)


	return list_to_return
	
