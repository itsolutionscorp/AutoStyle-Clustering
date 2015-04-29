import itertools

def transform(old):
		newdict={}
		for i in old:
			for k in old[i]:
				newdict[k.lower()]=i
		return newdict
			
