from re import search, finditer

_operations={'plus (-?\\d+)':lambda a,b:a+b, 'minus (-?\\d+)':lambda a,b:a-b, 'multiplied by (-?\\d+)':lambda a,b:a*b, 'divided by (-?\\d+)':lambda a,b:a//b, 'raised to the (-?\\d+)[a-z]{2} power':lambda a,b:a**b}

# answers question with an int, if it is correctly formatted and one of the supported question types.
# note: left-associates all operations
def calculate(question):
	operation_string = "(" + "|".join([ "("+matcher+")" for matcher in _operations.keys() ]) + ")"
	search_string = "^What is -?\\d+( " + operation_string + ")+\\?$"
	if not search(search_string,question):
		raise ValueError("Improperly formed question.")
	answer=int(search("(-?\\d+)",question).group(1))
	for r in finditer(operation_string,question):
		next_op = r.group(1)
		for matcher,operation in _operations.items():
			rr=search("^"+matcher+"$",next_op)
			if rr:
				answer=operation(answer,int(rr.group(1)))
	return answer
