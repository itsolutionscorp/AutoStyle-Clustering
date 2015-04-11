# extract-transform-load of a dict of int:list of str's into a dict of str:int
def transform(old):
	return { letter.lower():points for points in old.keys() for letter in old[points] }
