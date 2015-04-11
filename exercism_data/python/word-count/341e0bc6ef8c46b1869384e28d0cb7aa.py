def word_count(said) :
	parts= said.split()
	count={}
	for i in parts:
		if i in count:
			count[i]+=1
		else:
			count[i]=1
	return count
