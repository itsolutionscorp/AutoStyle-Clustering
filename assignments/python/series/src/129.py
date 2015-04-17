def slices(string, number):
	if number < 1 or number > len(string):
		raise ValueError
	string_list = []
	for x in range(len(string)-number+1):
		my_string = string[x:x+number]
		string_list.append([int(i) for i in my_string])
	return string_list
