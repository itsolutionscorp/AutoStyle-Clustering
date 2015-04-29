#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    	res = ""

	# DRY. Just in case our friend bob's answers become more verbose
	# and also for ease of maintenance in the future.
	sure = "Sure."
	whoa = "Whoa, chill out!"
	fine = "Fine. Be that way!"
	whatever = "Whatever."
	
	try:
		# strings are immutable so this is safe
		what = what.strip()
	except AttributeError:
		# if you speak in something other than words 
		# bob won't understand. Bob doesn't like that. 
		res = whatever
		# We return here because a finally would skip
		# the conditions and always return "".
		return res

	# Yelling rule first because ir supersedes the others.
	if what.isupper():
		res = whoa
	elif what == "":
		res = fine
	elif what.endswith("?"):
		res = sure
	else:
		res = whatever
	
	return res
