from __future__ import division

def on_square(n):
	return(2** (n -1))

def total_after(n):
	total = 0
	i = 1
	while i <= n:
		current = on_square(i)
		total += current
		i += 1
	return(int(total) )
