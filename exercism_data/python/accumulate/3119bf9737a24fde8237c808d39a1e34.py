def accumulate(collection, callback):
	return [callback(item) for item in collection]
