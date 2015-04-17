def slices(numstring,len_answer):
	answer = []	#storage for the answer
	if len(list(numstring)) < int(len_answer):
		raise ValueError("You have asked for more chars than are provided")
		return
	elif int(len_answer)==0:
		raise ValueError("You've asked for a zero length answer")
		return
	for i in range(len(list(numstring))+1-len_answer):	#we only want to iterate (n-m) times, to ensure we do not leave the array size
		answer.append(list(numstring[i:i+len_answer:1]))	#add THIS answer as a list to the answer list
		for i in range(len(answer[-1])):
			answer[-1][i] = int(answer[-1][i])	#convert all ansers from string to int
	return answer
