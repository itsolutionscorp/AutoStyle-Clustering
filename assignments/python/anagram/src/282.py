def detect_anagrams(wortold,init_wortlist):
	wortlist = []
	wort = wortold.lower()
	for i in range(0,len(init_wortlist)):
		wortlist.append(init_wortlist[i].lower())
	
	
	newlist = []
	for i in range(0, len(wortlist)):
		if len(wort) == len(wortlist[i]):
			test = True
			for k in range(0,len(wort)):
				if (wort.count(wort[k]) !=  wortlist[i].count(wort[k])):
					test = False
			if test == True and wortlist[i] != wortold:
				newlist.append(init_wortlist[i])
	return newlist
