'''etl.py
	created 20 Oct 2014
	by @jestuber '''

def transform(old):
	result = {}
	for key, val_list in old.iteritems():
		for value in val_list:
			result[value.lower()] = key
	return result
