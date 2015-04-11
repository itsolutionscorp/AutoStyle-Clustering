def to_rna(i):
	answer = ""
	for blah in i:
		if "C" in blah:
			answer += "G"
		elif "G" in blah:
			answer += "C"
		elif "T" in blah:
			answer += "A"
		elif "A" in blah:
			answer += "U"
	return answer
