def word_count(string):
	l=string.split()
	d={}
	for x in l:
		if d.has_key(x):
			d[x]+=1
		else:
			d[x]=(1)
	return d
	
