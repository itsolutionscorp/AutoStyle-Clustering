import re

def to_rna(sequence):
	return re.sub(" ","G",
								re.sub("G","C",
								re.sub("C"," ",
								re.sub("T","A",
								re.sub("A","U",sequence)))))
