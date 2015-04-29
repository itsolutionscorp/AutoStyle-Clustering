#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
		#If there is no characters then return no talking case
		if len(what.strip()) == 0:
			return('Fine. Be that way!')
		#Checks the entire string for a lower case value to see if Bob yells or asks a question
		quiet = False
		quest = False
		allNum = True
		for i in what:
			if i.islower():
				quiet = True
				allNum = False
				break
			if i.isalpha():
				allNum = False


		#Check for question mark at the end
		if what.strip()[len(what.strip())-1] == '?':
			quest = True

		if quiet == False and allNum == False:
			return('Whoa, chill out!')	
		elif quest == True:
			return('Sure.')
		else:
			return('Whatever.')
