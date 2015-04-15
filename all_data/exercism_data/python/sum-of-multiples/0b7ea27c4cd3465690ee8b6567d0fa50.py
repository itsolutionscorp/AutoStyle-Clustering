def sum_of_multiples(var, *vares):
	n=1
	numbers=[]
	div=[3,5]
	if vares:
		div=[]
		for x in vares:
			for y in x:
				if y != 0:
					div.append(y)
	while n<var:		
		for y in div:
			if n%y==0:
				numbers.append(n)
		n+=1
	return sum(numbers)

#print sum_of_multiples(10, [0, 1])
