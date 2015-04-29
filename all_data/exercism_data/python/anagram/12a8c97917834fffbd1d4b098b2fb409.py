def detect_anagrams(wrd, strng):
	ans = []
	l=[]
	wrd = wrd.lower()
	for i in range(len(wrd)):
		l.append(wrd[i])
	l.sort()
	w = []
	for i in strng:
		if len(l)==len(i) and i.lower()!=wrd:
			for j in range(len(i)):
				w.append(i[j].lower())
			w.sort()
			if w==l:
				ans.append(i)
		w=[]
	return ans
	
