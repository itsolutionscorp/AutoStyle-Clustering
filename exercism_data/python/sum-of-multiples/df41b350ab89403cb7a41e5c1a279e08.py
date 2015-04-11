def check_if_multiple(test_num,list_of_multiples):
	for i in list_of_multiples:
		if not i:
			continue
		if not test_num%i:
			return test_num
	return 0

def sum_of_multiples(number, multiples_list = None):
	if multiples_list == None:
		multiples_list = [3,5]
	answer=0
	for i in range(1,number):
		answer+=check_if_multiple(i,multiples_list)
	return answer
