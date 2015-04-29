def parse_binary(text):
	if any(c not in '01' for c in text): raise(ValueError)
	return reduce(
		lambda dec, c: dec * 2 + c,
		map(int, text)
	)
