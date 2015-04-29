def to_rna(strand):
	key = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	res = []

	i = 0 
	while i <= len(strand)-1:
		res.append(key.get(strand[i]))
		i += 1

			
	res = ''.join(res)
	return res
	
