def to_rna(string):
	my_list = list(string)
	char = 0
	while char < len(my_list):
		if my_list[char] == 'G':
			my_list[char] = 'C'
			char += 1
		elif my_list[char] == 'C':
			my_list[char] = 'G'
			char += 1 
		elif my_list[char] == 'T':
			my_list[char] = 'A'
			char += 1
		else:
			my_list[char] = 'U'
			char += 1 
	return ''.join(my_list)
