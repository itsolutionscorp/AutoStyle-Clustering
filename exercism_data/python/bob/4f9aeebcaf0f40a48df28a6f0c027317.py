def hey(what):
	what = what.strip()

	if len(what) == 0:
		return 'Fine. Be that way!'

	else:
		bitmask = 0
		bits = {'ends_with_?':1,'all_caps':2}

		if what[-1] == '?':
			bitmask |= bits['ends_with_?']

		if what.isupper():
			bitmask |= bits['all_caps']

    	return {0: 'Whatever.', 1: 'Sure.'}.get(bitmask,'Whoa, chill out!')
