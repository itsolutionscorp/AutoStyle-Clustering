def transform(input):
	output = {} # initialize dictionary
	for num in input.keys():
		for count in range(len(input[num])):
			newkey = input[num][count].lower()
			output[newkey] = num # adds new key to dictionary
	return output
