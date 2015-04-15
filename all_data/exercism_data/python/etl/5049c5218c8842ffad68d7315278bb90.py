def transform(dictionary):
	output = {}
	for k,v in dictionary.items():
		output = dict(output.items() + {item.lower():k for item in v}.items())
	return output
