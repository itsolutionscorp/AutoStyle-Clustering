def hey(what):

    ##If nothing is said
    if (what.isspace()) or (string_length==0):
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
