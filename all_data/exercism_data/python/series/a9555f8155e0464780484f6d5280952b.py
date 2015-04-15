def slices(num, length):
	if len(num) < length or not length:
		raise ValueError

	num = map(int, list(num))
	res = []
	for start in range(0, len(num)-length+1):
		res.append(num[start:start+length])

	return res 
