def to_rna(strand):
	dic = {"G" : "C", "C" : "G", "T": "A", "A" : "U"}

	res = ""

	for nuc in strand:
		res += dic[nuc]

	return res
