def transform(old):

	return {
		item.lower():key
		for key, val in old.items()
		for item in list(val)
	}
