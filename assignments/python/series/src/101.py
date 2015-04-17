def slices(lista,num):
	serie=[]
	new=[]
	tam=len(lista)
	comb=tam-num+1
	for i in range(comb):
		serie.append(list(lista[i:num+i]))
	for x in serie:
		new.append(map(int, x))
	if tam < num or num == 0:
		raise ValueError("Invalid slice length for this series")
	return new
