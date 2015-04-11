def next_line(line):
	next = [ 1 ]
	for ix in range(1, len(line)):
		next.append(line[ix] + line[ix-1])
	next.append(1)
	return next

def triangle(num):
	next = [ [ 1 ] ]
	out = [ '1' ]
	for ix in range(1, num+1):
		next = next_line( next )
		out.append( ' '.join(map(str, next)) )
	return out

def row(num):
	tri = triangle(num)
	return tri[len(tri) - 1]

def is_triangle(test):
	comp = triangle(len(test)-1)
	for ix in range(len(test)):
		if test[ix] != comp[ix]:
			return False
	return True
