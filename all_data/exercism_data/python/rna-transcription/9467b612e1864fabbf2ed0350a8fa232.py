def to_rna(dnastring):
	rna = {"G": "C", "C": "G", "T":"A", "A":"U"}
	try:
		return "".join([rna[i] for i in dnastring])
	except KeyError:
		return "DNA is invalid."
