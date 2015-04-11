def hey(what):
	# Strip whitespace, and then check for empty case.
	what = what.strip()

	if len(what) == 0:
		return 'Fine. Be that way!'

	else:
		# Non-empty input. Now, check for all caps, question mark, or both.
		# We will track which of these it has using a simple bitmask.
		bitmask = 0

		# Create bitmasks for each relevant condition.
		bits = {'ends_with_?':1,'all_caps':2}

		# Check if input ends in a questionmark...
		if what[len(what)-1] == '?':
			bitmask |= bits['ends_with_?']

		
		# Check if the input is uppercase...
		if what.isupper():
			bitmask |= bits['all_caps']

		# We are done. Let's evaluate the possible bitmask states:
		# 	0: Non-empty input		'Whatever.'
		# 	1: Standard question	'Sure.'
		# 	2: Yelling				'Whoa, chill out!'
		# 	3: Yelling a question	'Whoa, chill out!'
		# Since the last cases are identical, we can set that as a default, and supply the other two cases uniquely.
    	return {0: 'Whatever.', 1: 'Sure.'}.get(bitmask,'Whoa, chill out!')
