def parse_binary(strNum):
	# Open to suggestions for improvements to this condition
	if any(i not in '01' for i in strNum):
		raise ValueError('Must be a binary string representation')
	
	# This for loop seemed a straightforward way. 
	# May not results in the best performance
	# but for a representation perspective works great.
	num = 0
	k = 0
	for i in strNum[::-1]:
		num += 2**k*(i=='1')
		k+=1
	return num
