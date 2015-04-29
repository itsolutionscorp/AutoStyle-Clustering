d = {'0':[" _ ","| |","|_|","   "], '1':["   ","  |","  |","   "]}

def number(input):
	for k,v in d.items():
		if input == v:
			return k
	isvalid = len(input) + sum([1 for line in input if len(line)==3])
	if isvalid != 8:
		raise ValueError('invalid input')
	return '?'
	
def grid(input):
	if input in d:
		return d[input]
	
