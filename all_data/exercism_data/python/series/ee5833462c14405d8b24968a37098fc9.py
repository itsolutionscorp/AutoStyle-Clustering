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
