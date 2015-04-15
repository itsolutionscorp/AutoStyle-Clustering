def slices(numstring,len_answer):
	answer = []	#storage for the answer
	
	if len(list(numstring)) < int(len_answer):
		raise ValueError("You have asked for more chars than are provided")
		
	elif int(len_answer)==0:
		raise ValueError("You've asked for a zero length answer")
		
	
	for i in range(len(list(numstring))-len_answer+1):	#we only want to iterate (n-m) times, to ensure we do not leave the array size

		answer.append(list(numstring[i:i+len_answer:1]))	#add THIS answer as a list to the answer list
		
		answer[-1] = [int(x) for x in answer[-1]]			#list comprehension to apply the int() function - feels more pythonic than before
		#map(int,answer[-1])

	return answer
