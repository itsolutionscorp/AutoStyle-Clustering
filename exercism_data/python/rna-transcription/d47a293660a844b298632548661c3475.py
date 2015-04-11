def to_rna(strand):
	table = str.maketrans('ACGTacgt','UGCAugca')
	return strand.translate(table)
