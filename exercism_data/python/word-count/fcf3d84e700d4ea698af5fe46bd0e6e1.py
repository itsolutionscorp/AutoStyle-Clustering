def word_count(sentence):

	# Create output dictionary	
	output = {}

	# For each word
	for s in sentence.split():
		# Increment dictionary entry
		# if it exists
		if s in output.keys():
			output[s] += 1
		# Create dictionary entry
		# if it does not exist
		else:
			output[s] = 1

	return output	
