#
# Skeleton file for the Python "Bob" exercise.
# Author = Sung Sik Kim 
#
def hey(what):
		# strips white space!
		temp = what.strip()
		
		# lets check if you even say anything
		if len(temp) is 0:
			return 'Fine. Be that way!'
			
		# you did say something
		else:
			# Yelling 
			if temp.isupper():
				return 'Whoa, chill out!'
			
			# Asking question in a non-aggressive manner
			elif temp[-1] == '?' and not temp.isupper():

				return 'Sure.'
			
			# Miscellaneous 
			else:
	
				return 'Whatever.'
    
