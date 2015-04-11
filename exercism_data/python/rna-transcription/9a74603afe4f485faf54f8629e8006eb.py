def to_rna(s):
	trans = {
		"G": "C",
		"C": "G",
		"T": "A",
		"A": "U",
		"U": "A"
	}
	r = ""
	for si in s:
		if si:
			r += trans[si]
	return r

if __name__ == '__main__':
	print to_rna('C')
	print to_rna('UGCA')
