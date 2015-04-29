#
# Skeleton file for the Python "Bob" exercise.
#
class Bob:
	def __init__(arg):
		return

	def hey(self, what):
		rtnResponse = "Whatever."
		what = what.strip()

		if len(what) == 0:
			rtnResponse = "Fine. Be that way!"
		elif what.isupper():
			rtnResponse = "Woah, chill out!"
		elif what.endswith("?"):
			rtnResponse = "Sure."

		return rtnResponse
