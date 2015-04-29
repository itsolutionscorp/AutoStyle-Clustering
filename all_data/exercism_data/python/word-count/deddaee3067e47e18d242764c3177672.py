def word_count(phrase):
	
	#Split string input into list of words
	phrase = phrase.split()
	
	#Declare empty dictionary for storage of results
	wordCounts = {}
	
	#Iterate through all of the words in the phrase list
	for word in phrase:
		
		#Check if current word from phrase list is in the wordCounts dictionary and
		#increment its corresponding value if it is.
		if word in wordCounts:
			wordCounts[word] += 1
		
		#If the current word from phrase is not in the wordCounts dictionary, add it
		#to the dictionary as a key and set its value to 1.
		else:
			wordCounts[word] = 1
			
	#Return the wordCounts dictionary that contains key:value pairs of unique words and
	#their values.
	return wordCounts
