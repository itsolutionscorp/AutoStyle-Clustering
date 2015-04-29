def accumulate(collection, operator):
	return [operator(item) for item in collection]
