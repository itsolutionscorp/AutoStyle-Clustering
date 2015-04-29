#
# Skeleton file for the Python "Bob" exercise.
	#

def hey(sentence):
    if not sentence.strip():
        return 'Fine. Be that way!'
    elif sentence.isupper():
        return 'Whoa, chill out!'
    elif sentence.endswith('?'):
        return 'Sure.'
    else: 
        return 'Whatever.'
