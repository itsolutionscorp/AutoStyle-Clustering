def encode(text):
	# Import ceiling function
	from math import ceil
	# Remove extraneous characters with _normalize
	text = _normalize(text)
	# Set up the table
	l = int(ceil(len(text)**.5))
	# Fill in the table
	table = [[text[i*l+j] for i in range(l) if i*l+j < len(text)] for j in range(l)]
	# Join the table (note the space ' ' gets joined between each element)
	return ' '.join(''.join(col for col in row) for row in table)


# Name normalize with _ beginning prevents importation in module
def _normalize(text):
	# Define range including ords for lowercase and integers
	chars = range(97,123) + range(48,58)
	return ''.join(i for i in text.lower() if ord(i) in chars)
