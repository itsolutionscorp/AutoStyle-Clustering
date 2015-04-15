#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
		#If there is no characters then return no talking case
		if not what.strip():
			return('Fine. Be that way!')

		#Checks if the string is all capitals
		if what.isupper():
			return('Whoa, chill out!')

		#Check for question mark at the end and returns case
		if what.strip().endswith('?'):
			return('Sure.')

		#Once we have checked all other cases then we have final case
		return('Whatever.')
