#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

		answer=""

		answers={
			"nothing":"Fine. Be that way!",
			"yell":"Whoa, chill out!",
			"question":"Sure.",
			"else":"Whatever.",
		}

		what=what.strip()
		if what == "":
			answer=answers["nothing"]
		else:
			if what.isupper(): #and what[-1] == "!":
				answer=answers["yell"]
			elif what[-1] == "?":
				answer=answers["question"]
			else:
				answer=answers["else"]

		return answer
