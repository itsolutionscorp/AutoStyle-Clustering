def hey(what):

    ##If nothing is said
    if (what.isspace()):
 		return "Fine. Be that way!"

    ##If all characters are upper case (yelling)
    elif what.isupper():
		return "Whoa, chill out!"

    ##If it ends in a question mark (question being asked)
    elif what[-1]=="?":
		return "Sure."

    ##All other cases
    else:
		return "Whatever."
