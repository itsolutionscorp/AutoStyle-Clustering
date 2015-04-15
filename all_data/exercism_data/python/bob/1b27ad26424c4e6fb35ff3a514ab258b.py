import string

### Bob's possible responses
bob_question_response = "Sure."
bob_yell_response = "Whoa, chill out!"
bob_nothing_response = "Fine. Be that way!"
bob_anything_else_response = "Whatever."

"""
Method that returns Bob's response according to how you address him. Different 
responses depending on whether you "yell" (all caps) at him, ask him a question 
(end statement with "?"), say nothing, or anything else.

@param : what - String representation of what Bob hears when you address him. 
		 		Either a question, a "yell" (exclamatory expression), an empty 
		 		statement (i.e. saying nothing), or anything else.
@return : String representation of Bob's response. Either of the responses 
		  defined above.
"""
def hey(what):
	whatWithoutWhitespaceList = [] # List of non-whitespace characters in what
	hasLetters = False # what has letters (upper or lower)?

	# Build non-whitespace character list
	for i in what: 
		if i not in string.whitespace:
			whatWithoutWhitespaceList.append(i)
	
	# Define non-whitespace string rep of what
	whatWithoutSpace = string.join(whatWithoutWhitespaceList,"") 

	# Determine if what has letters
	for i in whatWithoutSpace:
		if i in string.letters:
			hasLetters = True
			break

	# Return Bob's response depending on characteristics of non-whitespace what
	if whatWithoutSpace == "": # Statement was all whitespace
		return bob_nothing_response
	elif whatWithoutSpace == string.upper(whatWithoutSpace) and hasLetters: # In all caps and has letters
		return bob_yell_response
	elif whatWithoutSpace[-1] == "?": # Statement is question
		return bob_question_response
	else: # Statement is anything-else
		return bob_anything_else_response
