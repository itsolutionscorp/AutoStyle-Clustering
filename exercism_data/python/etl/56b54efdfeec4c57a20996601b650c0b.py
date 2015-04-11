def transform(old):
	new = {}
	for key, value in old.items():
		for letter in value:
			new[letter.lower()]=key
	return new
