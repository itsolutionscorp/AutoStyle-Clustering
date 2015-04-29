import itertools

def slices(iterable, step):
	return [iterable[start:start+step] for start in range(0, len(iterable), step)]

def concat(list_of_lists):
	a = []
	for sublist in list_of_lists:
		a += sublist
	return a

def band_to_tuples(band):
	if len(band) != 4:
		raise ValueError("Bad banner size")
	
	width = len(band[0])
	if any(len(line) != width for line in band):
		raise ValueError("Bad banner size")

	chunks = [slices(line, 3) for line in band]
	return [tuple(concat(rows)) for rows in zip(*chunks)]

def tuples_to_band(tuples):
	chunks = [["".join(sl) for sl in slices(t,3)] for t in tuples]
	return ["".join(chunk) for chunk in zip(*chunks)]


font = [
	" _     _  _     _  _  _  _  _ ",
	"| |  | _| _||_||_ |_   ||_||_|",
	"|_|  ||_  _|  | _||_|  ||_| _|",
	"                              ",
]

tuple_to_character = {t : str(n) for t, n in zip(band_to_tuples(font), itertools.count())}
character_to_tuple = {a : b for b, a in tuple_to_character.items()}

def number(band):
	return "".join(tuple_to_character.get(t, '?') for t in band_to_tuples(band))

def grid(numbers):
	return tuples_to_band(character_to_tuple[nr] for nr in numbers)
