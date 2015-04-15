def transform(legacy_points):
	results = {}
	for key, value in legacy_points.iteritems():
		for word in value:
			results[word.lower()] = key
	return results
