def hey(quote):

	"""I may have manipulated the .upper and .lower string functions,
	1) As a method of differentiating between numbers sentences.
          
    2) As a method of differentiating between calm and shouting.
	
	In both regards, shout analysis is number/symbol-agnostic.

   
    Function assumes ending punctuation is present as intended

	"""

#Solve for: Fine! Be that way.
	
	#Test for not saying anything
	if not quote:
		return "Fine. Be that way!"

	#Strip the string, then test for empty. FBTW category solved/removed.
	unquote = quote.strip()
	
	if not unquote:
		return "Fine. Be that way!"



#Solve for: Sure.
	
	#Check for string ending in ?
	if quote[len(quote)-1] == '?':

		#Check for all upper-case (forceful/shouting)
		if quote.upper() == quote:
	 		
			#If quote.lower() indicates all-number/symbol - Sure.
			#Else, indicates shouting exception- Whoah, chill out.
	 		if quote.lower() == quote:
	 			return "Sure."
	 		else:
	 			return "Whoa, chill out!"

	 	#Non-shouting and all-number questions - Sure category solved/removed
	 	else:
	 		return "Sure."


#Solve for: Whoa, chill out!


	# quote.upper() will select all remaining WCOs
	# check for all-number/symbol exceptions
	if quote.upper() == quote:

		# All-upper with '!' end punctuation considered shouting
		if quote[len(quote)-1] == '!':
			return "Whoa, chill out!"
		
		#Handle no-end-punctuation and all-number/symbol exceptions
		else:

			# quote.lower() isolates all-number/symbol exception
			if quote.lower() == quote:
				return "Whatever."

			# Remaining uppercase string indicates shouting.
			# WCO category solved/removed
			return "Whoa, chill out!"			

#Solve for: Whatever.
	# Only populated, non-question, non-shouting quotes remain.
	# All categories solved.
	return "Whatever."
