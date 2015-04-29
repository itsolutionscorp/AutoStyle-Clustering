def to_rna(input_strand):
	nucleotides = list(input_strand);
	response = '';
	#basically a CASE statement
	for s in nucleotides:
		if s == 'A':
			response = response + 'U';
		if s == 'T':
			response = response + 'A';
		if s == 'C':
			response = response + 'G';
		if s == 'G':
			response = response + 'C';
	return response;
