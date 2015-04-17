def slices(num,n):
	i = 0
	x = []
	new = []
	if n > len(num) or n == 0:
		try:
			raise ValueError('Wont Work MotherF')
		except ValueError:
			print "size of slice is bigger than whole from which it is sliced!, or zero!, idiot!"
	while i + n <= len(num):
		x.append(num[i:i+n])
		i += 1
	for b in range(len(x)):
		for i in x:
			digits = [int(i) for i in x[b]]
		new.append(digits)
	return new
