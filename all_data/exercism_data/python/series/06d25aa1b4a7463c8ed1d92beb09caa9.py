def slices(string, n):
	all_series = []
	if n > len(string) or n < 1:
		raise ValueError
		
	for i in range(len(string)-n+1):
		series = [int(x) for x in list(string[i:i+n])]
		all_series.append(series)
	return all_series
