def slices(string, num):
	result=[]
	if len(string) < num:
		raise ValueError, "input is invalid"
		return

	for n in range(len(string)-num+1):
		result.append(map(int, list(string[n:n+num])))
	#print result
	return result



def largest_product(string, num):
	if len(string)==0:
		return 1
	data = slices(string,num)
	result=0
	for ele in data:
		product= reduce(lambda x,y: x*y, ele)
		if product>result:
			result=product
	return result
