def to_rna(dnn):
	zuruck = ''
	for i in range (0,len(dnn)):
		if dnn[i] == 'G':
			zuruck = zuruck + 'C'
		if dnn[i] == 'C':
			zuruck = zuruck + 'G'
		if dnn[i] == 'T':
			zuruck= zuruck + 'A'
		if dnn[i] == 'A':
			zuruck = zuruck + 'U'
			
	return zuruck
