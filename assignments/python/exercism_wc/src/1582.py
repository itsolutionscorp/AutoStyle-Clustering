def word_count(sentence):
	output = {}
	for s in sentence.split():
		if s in output.keys():
			output[s] += 1
		else:
			output[s] = 1
	return output	
