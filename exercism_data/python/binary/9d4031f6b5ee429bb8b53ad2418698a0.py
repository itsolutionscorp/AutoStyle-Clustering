# parses string into binary.
# contra specifications, throws an exception if string is not valid input.
def parse_binary(string):
	if not isinstance(string,str):
		raise ValueError("parse_binary needs type str.")
	answer=0
	for i in range(len(string)):
		if string[-i-1]=='1':
			answer+=1<<i
		elif string[-i-1] not in {'0','1'}:
			raise ValueError("%s is not a valid binary representation of an integer."%string)
	return answer
