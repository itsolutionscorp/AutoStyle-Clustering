#
# Skeleton file for the Python "Bob" exercise.
#

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Whoa, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying
# anything.

# He answers 'Whatever.' to anything else.
__RESPONSES = {
	'blank': 'Fine. Be that way!',
	'yell': 'Whoa, chill out!',
	'question': 'Sure.',
	'other': 'Whatever.'	
}
def hey(what):
	what = what.strip()
	if not what: return __RESPONSES['blank']
	if what.isupper(): return __RESPONSES['yell']
	if what[-1] == '?': return __RESPONSES['question']
	return __RESPONSES['other']
