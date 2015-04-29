#
# Skeleton file for the Python "Bob" exercise.
#
class Bob:
	def hey(self,what):
		said = what[len(what)-1:]
		#print "said ["+said+"]", (said == '?')
		if what.isupper():
			return 'Woah, chill out!'
		elif said == "?":
			return 'Sure.'
		elif not said.strip():
			return 'Fine. Be that way!'
		else :
			return 'Whatever.'
		return

	def Bob():
	
		return
