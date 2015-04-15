def to_rna(string):
	"""returns translations of RNA from DNA string"""
	# dictionary of complementary characters from DNA to RNA
	comp={'G':'C','C':'G','T':'A','A':'U'}
	# translated string
	trans=''
	for item in string:
		if comp.has_key(item):
			temp=comp[item]
			trans+=temp
	return trans
