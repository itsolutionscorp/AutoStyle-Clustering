def hey(what):
    if len(what.strip()) == 0:
		return "Fine. Be that way!"
    if what.isupper():
            return 'Whoa, chill out!'
    if what[len(what.strip())-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
