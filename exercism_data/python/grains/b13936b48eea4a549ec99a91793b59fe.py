def on_square(numb):
	return 2**(numb-1)


def total_after(numb):
	z = 0
	for k in range(1,numb+1):
		z+=on_square(k)
	return z 
