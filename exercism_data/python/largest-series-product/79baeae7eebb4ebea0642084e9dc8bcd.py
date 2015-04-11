# translates the given string into a list of digits (of type int)
def extract_digits(string):
	answer=[]
	for d in string:
		answer.append(int(d))
	return answer

# calculates all the size-length slices of the given string interpreted as int's
# raises a ValueError if size>len(string) or size<=0
def slices(string,size):
	if size>len(string) or size<=0:
		raise ValueError("%s is not a valid size for slices of \"%s\"."%(size,string))
	answer=[]
	for i in range(len(string)-size+1):
		answer.append(extract_digits(string[i:i+size]))
	return answer

# calculates the product of all arguments (which should be of type int)
def product(*args):
	answer=1
	for n in args:
		answer*=n
	return answer

# calculates the largest product of all size-length slices of the given string interpreted as ints.
# raises a ValueError if size>len(string)
def largest_product(string,size):
	if len(string)==size==0:
		return 1
	else:
		return max([ product(*x) for x in slices(string,size) ])
