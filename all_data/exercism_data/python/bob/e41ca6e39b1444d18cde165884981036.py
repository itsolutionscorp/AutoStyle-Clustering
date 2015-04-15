#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what
	answer = {0:'Sure.',1:'Whoa, chill out!',2:'Fine. Be that way!',3:'Whatever.'}
	return  (not what.strip()and answer.get(2))or(what.isupper()and answer.get(1))or (what.endswith('?')and answer.get(0))or answer.get(3)
