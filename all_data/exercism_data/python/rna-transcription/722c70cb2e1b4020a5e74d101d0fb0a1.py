translationKeys = { 
"G":"C",
"C":"G",
"T":"A",
"A":"U"
}

def to_rna(DNA):
	rna = ""
	for char in DNA:
		rna = rna + translationKeys[char];
	return rna;
