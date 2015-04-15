def word_count(phrase):

	#Split the string into individual "words"
	split_string = phrase.split()

	#Create a dictionary to hold all count values 
	string_list = {}
	
	#Look for all words in dictionary
	for word in split_string:

		#If found, increase count, otherwise add it in
		if string_list.has_key(word):
			string_list[word] = string_list[word] + 1
		else:
			string_list[word] = 1


	return string_list
