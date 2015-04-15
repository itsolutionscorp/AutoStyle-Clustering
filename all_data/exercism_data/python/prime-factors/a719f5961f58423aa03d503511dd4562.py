def prime_factors(number):
	out =[]
	if number ==0:
		return out
	else:
		while number !=1:
			if len(out)==0:
				divide=find_lowest(number,2)
			else:
				divide=find_lowest(number,out[len(out)-1])
			
			out.append(divide)
			number/=divide
		return out
			
def find_lowest(number,prev):
	while prev < number+1:
		if number % prev ==0:
			return prev
		prev+=1
