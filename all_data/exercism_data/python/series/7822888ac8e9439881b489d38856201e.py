def slices(string, number):
	if number > len(string) or number < 1:
		raise ValueError('Number is too large or too small')

	answer = []
	times_ran=0
	for i in range(0, len(string)-(number-1)):
		temp_list=[]
		for p in range(times_ran, number+times_ran):
			temp_list.append(int(string[p]))
		times_ran+=1
		answer.append(temp_list)
	return answer
