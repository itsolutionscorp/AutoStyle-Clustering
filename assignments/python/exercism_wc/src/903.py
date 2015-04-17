def word_count(string):
	words = string.split()
	dicio = {}
	for i in words:
		if i in dicio:
			dicio[i]+=1
		if not i in dicio:
			dicio[i]=1
	return dicio
