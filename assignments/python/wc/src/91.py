def word_count(message):
	s = [' ']
	for c in message:
		if c in [' ', '\n', '\t', '\r']: # is white-space
			if s[-1] != ' ': # add delimiter unless last char was delimiter
				s += [' ']
		else: # use character as-is
			s += c 
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
