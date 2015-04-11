def slices(num_string,slice_size):
	if slice_size>len(num_string):
		raise ValueError
		
	list_of_slices = []
	for i in range(len(num_string)-slice_size+1):
		temp_answer = []
		for j in range(slice_size):
			temp_answer.append(int(num_string[i+j]))
		list_of_slices.append(temp_answer)
		
	return list_of_slices
	
def largest_product(num_string,slice_size):
	slices_to_test = slices(num_string,slice_size)
	answer = 1
	for i in range(len(slices_to_test)):
		working_value = 1
		
		for j in range(slice_size):
			working_value*=slices_to_test[i][j]
			
		if working_value > answer:
			answer = working_value
			
	return answer
