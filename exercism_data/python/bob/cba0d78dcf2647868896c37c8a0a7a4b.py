#
# Skeleton file for the Python "Bob" exercise.
#

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Whoa, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying
# anything.

# He answers 'Whatever.' to anything else.
__SILENCE_ANSWER = 'Fine. Be that way!'
__SCREAMING_ANSWER = 'Whoa, chill out!'
__QUESTION_ANSWER = 'Sure.'
__WHATEVER_ANSWER = 'Whatever.'
def hey(what):
	what = what.strip()
	if not what: return __SILENCE_ANSWER
	if what.isupper(): return __SCREAMING_ANSWER
	if what[-1] == '?': return __QUESTION_ANSWER
	return __WHATEVER_ANSWER
