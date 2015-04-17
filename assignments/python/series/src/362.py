def slices(lista,num):
	try:
		serie=[]
		new=[]
		comb=len(lista)-num+1
		for i in range(comb):
			serie.append(list(lista[i:num+i]))
		for x in serie:
			new.append(map(int, x))
		return new
	except ValueError:
		print "Deu merda"
print slices("01234", 0)
