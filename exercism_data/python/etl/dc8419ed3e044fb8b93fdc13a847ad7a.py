def transform(old):
		newdict={}
		for i in range(1,11):
				if i in old:
					for k in old[i]:
						newdict[k.lower()]=i
		return newdict
			
