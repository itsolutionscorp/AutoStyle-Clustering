def slices(arr, l):
	if l > len(arr) or l <= 0:
		raise ValueError ("Hii")
	temp = [int(i) for i in arr]
	final = []
	for x in range(len(arr) + 1 - l):
		final.append(temp[x:x+l])
	return final
