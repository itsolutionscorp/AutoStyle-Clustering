def check_if_multiple(test_num,list_of_multiples):
	for i in list_of_multiples:
		if not i:
			continue
		if not test_num%i:
			return test_num
	return 0

def sum_of_multiples(number, multiples_list = None):
	multiples_list = multiples_list or [3,5]
	#implicitly check if None is passed to the function
	
	
	return sum(list(filter(lambda x: check_if_multiple(x,multiples_list),range(1,number))))
	
	#the above line:
		#uses a lambda to call check_if_multiple multiple times with different values of x, fixing the mutliples list
		#filters out any returns that are false (returned zero)
		#lists the returned answers
		#and sums them together - as requested!
