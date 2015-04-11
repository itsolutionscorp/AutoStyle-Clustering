def word_count(message):
	# 1. remove all of the whitespace without using library call
	# and replace each with just a space.
	# 2. split on spaces, making list of words
	# 3. build dictionary with keys as the words

	# start with a leading space to ensure s[-1] is valid
	s = [' ']
	for c in message:
		if c in [' ', '\n', '\t', '\r']: # is white-space
			if s[-1] != ' ': # add delimiter unless last char was delimiter
				s += [' ']
		else: # use character as-is
			s += c 
			
	# get a list of words by joining the array and splitting on spaces
	words = ''.join(s).split(' ')
	
	dict = {}
	for word in words:
		if word == '':
			continue # get next word
		if word in dict:
			dict[word] += 1
		else: # not in dict
			dict[word] = 1
	
	return dict
	
	
