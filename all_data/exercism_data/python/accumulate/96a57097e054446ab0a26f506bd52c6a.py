def accumulate(inp, function):
	output = []
	for x in inp:
		output.append(function(x))
	return output
