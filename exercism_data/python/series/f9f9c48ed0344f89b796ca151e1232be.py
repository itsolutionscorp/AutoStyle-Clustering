def slices(serie, series_depth):
	if series_depth <= 0 or series_depth > len(serie):
		raise ValueError("length argument doesn't fit the serie")		
	
	collection = []
	for j in range(0,len(serie)-series_depth+1):
		subcollection = []
		for k in range(0,series_depth):
			subcollection.append(int(serie[j+k]))
		collection.append(subcollection)
	return collection
		
	
