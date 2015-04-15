
def to_rna(string):
	return ''.join(map(translate_char, string))


def translate_char(char):
	if char == "G":
		return "C"
	elif char == "C":
		return "G"
	elif char == "T":
		return "A"
	elif char == "A":
		return "U"

