def to_rna(dnaseq):
	dnaseq = dnaseq.upper()
	rnaseq = ""
	for bp in dnaseq:
		if bp == "G":
			rnaseq = rnaseq + "C"
		elif bp == "C":
			rnaseq = rnaseq + "G"
		elif bp == "T":
			rnaseq = rnaseq + "A"
		elif bp == "A":
			rnaseq = rnaseq + "U"
	return rnaseq
