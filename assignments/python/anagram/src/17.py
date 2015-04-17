def detect_anagrams(a,w_list):
	a_results = []
	for w in w_list: #three tests need to be True
		
		o = w
		
		a = a.lower()
		w = w.lower()
		test_1 = w != a		
		test_2 = len(w) == len(a)
		test_3 = sorted(a) == sorted(w)
		if test_1 and test_2 and test_3:
			a_results.append(o)
		
	return  a_results
	
