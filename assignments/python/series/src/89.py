def slices(num, length):
	if len(num) < length or not length:
		raise(ValueError)
	num = list(num)
	res = []
	for start in range(0, len(num)-length+1):
		part = []
		for n in range(start, start + length):
			part.append(int(num[n]))
		res.append(part)
	return res 
