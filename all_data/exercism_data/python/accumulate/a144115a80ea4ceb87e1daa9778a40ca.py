def accumulate(arr, fun):
	result = []
	for m in arr:
		result.append(fun(m))
	return result
