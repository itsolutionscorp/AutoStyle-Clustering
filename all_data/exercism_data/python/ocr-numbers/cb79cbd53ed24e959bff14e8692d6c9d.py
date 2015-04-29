_bits={'0':[" _ ", "| |", "|_|", "   "], '1':["   ", "  |", "  |", "   "]}

# translates lines into '0', '1' or '?' (default)
def number(lines):
	if not hasattr(lines,'__iter__') or len(lines)!=4:
		raise ValueError("Requires 4 lines as input.")
	if any([ len(line)!=3 for line in lines ]):
		raise ValueError("Every line must contain exactly 3 characters.")
	for character,digit in _bits.items():
		if digit==lines:
			return character
	return '?'

# translates '0' or '1' into lines
def grid(character):
	if character not in _bits.keys():
		raise ValueError("%s is not a recognized character."%character)
	return _bits[character]
