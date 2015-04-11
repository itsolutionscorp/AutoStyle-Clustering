__author__ = "me"

def slices(str_num, digits):
	"""
	::str_num:: An intenger number in string format.
	::digits:: An intenger number, must be less that str_num 
			   length, if not Raise ValueError.
	<return> all the possible consecutive number series of length `n`(digits) in that string
	"""
	if len(str_num) < digits or digits <= 0:
		raise ValueError
	r = []
	for i in range(len(str_num)):
		new_serie = []
		if i+digits <= len(str_num):
			for n in range(digits):
				new_serie.append(int(str_num[i + n]))
			r.append(new_serie)
	return r
