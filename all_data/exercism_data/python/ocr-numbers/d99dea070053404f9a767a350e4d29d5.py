import itertools

def slices(string, step):
	return [string[start:start+step] for start in range(0, len(string), step)]

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
	chunks = zip(*chunks)

	lines = ["".join(chunk) for chunk in chunks]
	return lines


font = """
 _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|
                              """[1:].splitlines()

tuple_to_number = {t : str(n) for t, n in zip(band_to_tuples(font), itertools.count())}
number_to_tuple = {a : b for b, a in tuple_to_number.items()}

def number(band):
	return "".join(tuple_to_number.get(t, '?') for t in band_to_tuples(band))

def grid(numbers):
	return tuples_to_band(number_to_tuple[nr] for nr in numbers)
