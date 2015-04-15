#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# I need to split this into parts
	parts = what.split(",")
	# These are my valid responses
	responses = {
		"takeItEasyMan": "Whoa, chill out!",
		"justAnswer": "Sure.",
		"dontIgnoreMe": "Fine. Be that way!",
		"whateva": "Whatever."
	}
	for p in parts:
		# I need to know if this is a question, so I can replace the part
		if p.endswith("?"):
			isQuestion = 1
			p = p[:-1]
		else:
			isQuestion = 0
		# Cases
		if not p.strip():
			return responses["dontIgnoreMe"]
		elif p.isupper() and not p.isdigit():
			return responses["takeItEasyMan"]
		elif isQuestion:
			return responses["justAnswer"]
	return responses["whateva"]			
