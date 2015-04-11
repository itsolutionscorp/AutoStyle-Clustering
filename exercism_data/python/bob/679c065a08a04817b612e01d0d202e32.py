#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    is_yell = False
    for c in what:
        if c.islower() is True:
            is_yell = False
            break
        elif c.isupper() is True:
            is_yell = True
    if is_yell:
        # Yell
        return 'Whoa, chill out!'
    elif len(what) == 0:
    	# Nothing
    	return 'Fine. Be that way!'
    elif what[-1] == '?':
        # Question
        return 'Sure.'
    else:
        # Other cases
        return 'Whatever.'
