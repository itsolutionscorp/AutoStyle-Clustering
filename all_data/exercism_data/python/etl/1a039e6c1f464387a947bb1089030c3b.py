def transform(data):
	result = {}
	for key, value in data.iteritems():
		for item in value:
			result[item.lower()] = key
	return result
