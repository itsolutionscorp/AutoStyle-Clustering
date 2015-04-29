def word_count(strng):
	wrdlist = strng.split()
	ans = dict()
	
	for i in wrdlist:
		if i in ans:
			ans[i]+=1
		else:
			ans[i]=1
	return ans
