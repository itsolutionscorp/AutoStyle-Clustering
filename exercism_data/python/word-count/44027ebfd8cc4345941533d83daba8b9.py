def word_count(input_string):
	string_list = input_string.split();
	response = {};
	for s in string_list:
		if s in response:
			response[s] = response[s] + 1;
		else:
			response[s] = 1;
		
	return response;
