def to_rna(get):
	new_word = []
	for i in list(get):
		print type(i)
		if i == 'G':
			new_word.append('C')
		elif i == 'C':
			new_word.append('G')
		elif i == 'T':
			new_word.append('A')
		elif i == 'A':
			new_word.append('U')
		elif i is not 'G' or 'T':
			#print i
		 	new_word.append(i)


	print ''.join(new_word)
