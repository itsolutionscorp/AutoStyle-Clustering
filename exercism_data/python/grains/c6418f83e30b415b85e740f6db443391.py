def on_square(x):
	d = make_dictionary(x)
	return d[x][0]
	
def total_after(x):
	d = make_dictionary(x)
	return d[x][1]

def make_dictionary(x):
	d = {1:[1,1]}
	for i in range(2,x+1):
		current = d[i-1][0]*2
		total = d[i-1][1]+current
		d[i] = [current,total]
	return d
