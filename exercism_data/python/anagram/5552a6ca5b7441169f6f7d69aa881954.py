def detect_anagrams(case_word, test_words):

	ana_list = []
	case_word = case_word.lower()

	for i in range(len(test_words)):

		if len(case_word)==len(test_words[i]) and case_word!=test_words[i].lower():
			
			test_list = list(test_words[i].lower())
			for x in list(case_word):
				if x in test_list and len(test_list)==1:
					ana_list.append(test_words[i])
				elif x in test_list:
					test_list.remove(x)
				else:
					break

	return ana_list
