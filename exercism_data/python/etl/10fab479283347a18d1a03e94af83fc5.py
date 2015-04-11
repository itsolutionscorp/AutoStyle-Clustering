def transform(dictionary):
	output = {}
	for k,v in dictionary.items():
		#updated using update function instead of recursively building the output dictionary
		output.update({item.lower():k for item in v})
	return output
